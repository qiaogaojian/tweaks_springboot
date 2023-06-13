package com.mega.tweaks.api.model.dao;

import com.mega.tweaks.api.model.entity.TestUser;

public interface TestUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);
}