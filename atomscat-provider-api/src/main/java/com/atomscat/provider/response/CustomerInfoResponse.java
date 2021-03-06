package com.atomscat.provider.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerInfoResponse implements Serializable {
    private String name;

    private String username;

    private String password;

    private String nickName;

    private String mobile;

    private String email;

    private String address;

    private Integer sex;

    private String avatar;

    private Integer type = 0;

    private Integer status = 0;

    private String description;
}
