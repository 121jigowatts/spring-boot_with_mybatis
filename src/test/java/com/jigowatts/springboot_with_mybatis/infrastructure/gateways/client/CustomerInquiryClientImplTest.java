package com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client;

import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.common.Notifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerInquiryClientImplTest {

    @Autowired
    CustomerInquiryClientImpl target;
    WireMockServer wiremock;

    @BeforeEach
    void setup() {
        Notifier notifier = new ConsoleNotifier(true);

        Options options = options().port(8081).notifier(notifier);
        wiremock = new WireMockServer(options);
        wiremock.start();
    }

    @Test
    @DisplayName("SOAP通信テスト")
    public void test() {
        wiremock.stubFor(post(urlEqualTo("/ws/customers"))
                .willReturn(aResponse().withStatus(200).withBodyFile("customer-response.xml")));

        String customerId = "X4";
        Optional<Customer> aCustomer = target.getCustomer(customerId);
        assertThat(aCustomer.isPresent()).isTrue();
        aCustomer.ifPresentOrElse(actual -> {
            assertThat(actual.getCustomerId()).isEqualTo("X4");
            assertThat(actual.getCustomerName()).isEqualTo("Emmet");
            assertThat(actual.getAddress()).isEqualTo("California");
        }, () -> {
            fail();
        });
    }

}