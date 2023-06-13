package com.mega.tweaks.api.service;

import com.mega.tweaks.api.model.dao.TestUserDao;
import com.mega.tweaks.api.model.entity.TestUser;
import com.mega.tweaks.api.model.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService
{
    private final TestUserDao testUserDao;

    @Autowired
    public UserService(TestUserDao testUserDao) {
        this.testUserDao = testUserDao;
    }

    public void addUsers(String name, String email) {
        testUserDao.insertUser(name, email);
    }

    public UserVO getAllUsers() {
        UserVO         userVO   = new UserVO();
        List<TestUser> userList = testUserDao.selectAll();
        userVO.setUserList(userList);
        return userVO;
    }
}
