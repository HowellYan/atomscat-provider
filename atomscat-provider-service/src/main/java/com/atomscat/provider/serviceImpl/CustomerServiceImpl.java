package com.atomscat.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atomscat.provider.entity.User;
import com.atomscat.provider.mapper.UserMapper;
import com.atomscat.provider.request.CustomerInfoRequest;
import com.atomscat.provider.response.CustomerInfoResponse;
import com.atomscat.provider.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
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


}
