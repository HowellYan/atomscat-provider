package com.atomscat.provider.service;

import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;

import java.util.List;

/**
 * 顾客
 */
public interface CustomerService {

    CustomerInfoResponse getCustomerInfo(CustomerInfoRequest customerInfoRequest);

    CustomerInfoResponse setRedis(CustomerInfoRequest customerInfoRequest);

    List<CustomerInfoResponse> getCustomerInfoList(CustomerInfoRequest customerInfoRequest);

    List<CustomerInfoResponse> test(CustomerInfoRequest customerInfoRequest) throws Exception;

    List<CustomerInfoResponse> test2(CustomerInfoRequest customerInfoRequest) throws Exception;
}
