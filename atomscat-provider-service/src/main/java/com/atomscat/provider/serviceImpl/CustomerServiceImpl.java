package com.atomscat.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;
import com.atomscat.provider.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerInfoResponse getCustomerInfo(CustomerInfoRequest customerInfoRequest) {
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        customerInfoResponse.setName(customerInfoRequest.getName());
        return customerInfoResponse;
    }

}
