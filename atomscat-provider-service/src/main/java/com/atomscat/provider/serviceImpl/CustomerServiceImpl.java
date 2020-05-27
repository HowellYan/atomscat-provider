package com.atomscat.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;
import com.atomscat.provider.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public CustomerInfoResponse getCustomerInfo(CustomerInfoRequest customerInfoRequest) {
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        customerInfoResponse.setName(customerInfoRequest.getName());
        return customerInfoResponse;
    }

    @Override
    public CustomerInfoResponse setRedis(CustomerInfoRequest customerInfoRequest) {
        stringRedisTemplate.opsForValue().set("key_string", customerInfoRequest.getName());
        redisTemplate.opsForValue().set("key_obj", customerInfoRequest);
        return null;
    }


}
