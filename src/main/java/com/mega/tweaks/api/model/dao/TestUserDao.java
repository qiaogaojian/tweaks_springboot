package com.mega.tweaks.api.model.dao;

import com.mega.tweaks.api.model.entity.TestUser;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestUserDao
{
    void insertUser(@Param("name") String name, @Param("email") String email);

    List<TestUser> selectAll();
}