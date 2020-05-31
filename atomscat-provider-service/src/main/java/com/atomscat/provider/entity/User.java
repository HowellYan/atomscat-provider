package com.atomscat.provider.entity;

import lombok.Data;

@Data
public class User {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String nickName;

    private String mobile;

    private String email;

    private String address;

    private Integer sex;

    private String avatar = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    private Integer type = 0;

    private Integer status = 0;

    private String description;
}
