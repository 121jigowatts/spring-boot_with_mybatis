package com.jigowatts.springboot_with_mybatis.infrastructure.gateways.config;

import com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client.CustomerInquiryClient;
import com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client.CustomerInquiryClientImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lombok.Getter;

@Configuration
@PropertySource("classpath:soap.properties")
@Getter
public class SoapConfiguration {
    @Value("${config.contextPath}")
    private String contextPath;
    @Value("${config.defaultUri}")
    private String defaultUri;

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        var marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(this.contextPath);
        return marshaller;
    }

    @Bean
    public CustomerInquiryClient customerInquiryClient(Jaxb2Marshaller marshaller) {
        var client = new CustomerInquiryClientImpl();
        init(client, marshaller);
        return client;
    }

    private void init(WebServiceGatewaySupport client, Jaxb2Marshaller marshaller) {
        client.setDefaultUri(this.defaultUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
    }
}