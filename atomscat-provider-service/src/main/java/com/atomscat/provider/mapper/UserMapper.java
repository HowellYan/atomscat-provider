package com.atomscat.provider.mapper;

import com.atomscat.provider.config.annotation.DataSource;
import com.atomscat.provider.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    List<User> select();

}
