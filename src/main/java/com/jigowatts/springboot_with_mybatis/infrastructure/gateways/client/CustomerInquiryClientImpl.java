package com.jigowatts.springboot_with_mybatis.infrastructure.gateways.client;

import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.customer.Customer;
import com.jigowatts.springboot_with_mybatis.infrastructure.wsdl.GetCustomerRequest;
import com.jigowatts.springboot_with_mybatis.infrastructure.wsdl.GetCustomerResponse;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CustomerInquiryClientImpl extends WebServiceGatewaySupport implements CustomerInquiryClient {

    @Override
    public Optional<Customer> getCustomer(String customerId) {

        GetCustomerRequest request = new GetCustomerRequest();
        request.setId(customerId);

        GetCustomerResponse response = (GetCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(
                String.format("%s/customers", this.getDefaultUri()), request,
                new SoapActionCallback("http://example.com/soap_server/GetCustomerRequest"));

        com.jigowatts.springboot_with_mybatis.infrastructure.wsdl.Customer aCustomer = response.getCustomer();

        return Optional.ofNullable(Customer.builder().customerId(aCustomer.getId()).customerName(aCustomer.getName())
                .address(aCustomer.getAddress()).build());

    }

}