package com.atomscat.provider.service;

import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;

/**
 * 顾客
 */
public interface CustomerService {

    CustomerInfoResponse getCustomerInfo(CustomerInfoRequest customerInfoRequest);
}
