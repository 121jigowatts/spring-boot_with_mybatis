package com.jigowatts.springboot_with_mybatis.application.service;

import static com.jigowatts.springboot_with_mybatis.application.service.helper.AddressHelper.address_id_1;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.AddressHelper.address_id_2;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.CustomersHelper.customers_id_1;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.CustomersHelper.customers_id_2;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.ItemsHelper.items_id_1;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.ItemsHelper.items_id_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import com.jigowatts.springboot_with_mybatis.application.component.AddressComponent;
import com.jigowatts.springboot_with_mybatis.application.component.CustomerComponent;
import com.jigowatts.springboot_with_mybatis.application.component.ItemComponent;
import com.jigowatts.springboot_with_mybatis.domain.model.address.Address;
import com.jigowatts.springboot_with_mybatis.domain.model.complexdata.ComplexData;
import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Bag;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Glasses;
import com.jigowatts.springboot_with_mybatis.helper.yaml.YamlLoader;
import com.jigowatts.springboot_with_mybatis.helper.yaml.constructor.ComplexDataConstructor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MessagesServiceTest
 */
@SpringBootTest
public class ComplexDataServiceTest {
    @InjectMocks
    ComplexDataService target;
    @Mock
    private AddressComponent addressComponent;
    @Mock
    private CustomerComponent customerComponent;
    @Mock
    private ItemComponent itemComponent;

    private YamlLoader<ComplexData> yamlLoader;

    @BeforeEach
    void setup() {
        yamlLoader = new YamlLoader<>();
    }

    @Test
    public void findByIdTest() throws IOException {
        String id = "1";
        when(addressComponent.getBy(id)).thenReturn(address_id_1());
        when(customerComponent.getBy(id)).thenReturn(customers_id_1());
        when(itemComponent.getBy(id)).thenReturn(items_id_1());

        var yml = "fixture/complexdata/findByIdTest.yml";

        var expected = yamlLoader.load(yml, new ComplexDataConstructor());

        var actual = target.findById(id);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("ケースごとにメソッドでfixtureを用意する")
    public void testcase_01() {
        String id = "1";
        when(addressComponent.getBy(id)).thenReturn(address_id_1());
        when(customerComponent.getBy(id)).thenReturn(customers_id_1());
        when(itemComponent.getBy(id)).thenReturn(items_id_1());

        var expected = expected_01();

        var actual = target.findById(id);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Switch文などでfixtureを用意する")
    public void testcase_02() {
        int caseNo = 2;
        String id = "2";
        when(addressComponent.getBy(id)).thenReturn(address_id_2());
        when(customerComponent.getBy(id)).thenReturn(customers_id_2());
        when(itemComponent.getBy(id)).thenReturn(items_id_2());

        var expected = expected(caseNo);

        var actual = target.findById(id);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private ComplexData expected_01() {
        // 複雑なオブジェクトの生成
        return ComplexData.builder().id("1")
                .address(Address.builder().id("1").zip("12345").street("f.street").city("sand").country("EOL").build())
                .customers(List.of(Customer.builder().customerId("1").customerName("customer1").build(),
                        Customer.builder().customerId("2").customerName("customer2").build()))
                .items(List.of(Glasses.builder().id("1").lens("blue").build(),
                        Bag.builder().id("2").capacity("L").build()))
                .build();
    }

    private ComplexData expected(int caseNo) {
        switch (caseNo) {
            case 1:
                // 複雑なオブジェクトの生成
                return ComplexData.builder().id("1")
                        .address(Address.builder().id("1").zip("12345").street("f.street").city("sand").country("EOL")
                                .build())
                        .customers(List.of(Customer.builder().customerId("1").customerName("customer1").build(),
                                Customer.builder().customerId("2").customerName("customer2").build()))
                        .items(List.of(Glasses.builder().id("1").lens("blue").build(),
                                Bag.builder().id("2").capacity("L").build()))
                        .build();
            case 2:
                // 複雑なオブジェクトの生成
                return ComplexData.builder().id("2")
                        .address(Address.builder().id("2").zip("22345").street("g.street").city("sky").country("EPL")
                                .build())
                        .customers(List.of(Customer.builder().customerId("3").customerName("customer3").build(),
                                Customer.builder().customerId("4").customerName("customer4").build()))
                        .items(List.of(Glasses.builder().id("3").lens("red").build())).build();
            default:
                return ComplexData.builder().build();
        }
    }
}