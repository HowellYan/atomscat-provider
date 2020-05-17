package com.atomscat.provider.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerInfoRequest implements Serializable {
    String name;
}
