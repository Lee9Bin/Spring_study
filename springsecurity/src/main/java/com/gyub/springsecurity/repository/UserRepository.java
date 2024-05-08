package com.gyub.springsecurity.repository;


import com.gyub.springsecurity.domain.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserRepository{
    User findByEmail(String email);
    void save(User user);
}