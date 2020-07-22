package com.atomscat.provider.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.atomscat.provider.config.annotation.DataSource;
import com.atomscat.provider.entity.User;
import com.atomscat.provider.mapper.UserMapper;
import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;
import com.atomscat.provider.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@DubboService
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

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
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        customerInfoResponse.setName(stringRedisTemplate.opsForValue().get("key_string"));
        log.info(customerInfoRequest.getName());
        return customerInfoResponse;
    }

    @Override
    @DataSource(name = "atomscat-provider")
    public List<CustomerInfoResponse> getCustomerInfoList(CustomerInfoRequest customerInfoRequest) {
        List<User> userList = userMapper.select();
        List<CustomerInfoResponse> customerInfoResponseList = new ArrayList<>();
        for (User user: userList) {
            CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
            BeanUtils.copyProperties(user, customerInfoResponse);
            customerInfoResponseList.add(customerInfoResponse);
        }
        return customerInfoResponseList;
    }

    @Override
    public List<CustomerInfoResponse> test(CustomerInfoRequest customerInfoRequest) {
        log.info(JSONObject.toJSONString(customerInfoRequest));
        List<CustomerInfoResponse> list = new ArrayList<>();
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        customerInfoResponse.setName("test");
        list.add(customerInfoResponse);
        return list;
    }


}
