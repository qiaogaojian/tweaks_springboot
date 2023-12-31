package com.mega.tweaks.controller;

import com.mega.tweaks.model.vo.Result;
import com.mega.tweaks.model.vo.Results;
import com.mega.tweaks.model.vo.UserVO;
import com.mega.tweaks.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "MySql 测试")
@RestController
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("添加用户")
    @PostMapping("/user/add")
    public Result<?> addUser(@RequestParam String name, @RequestParam String email) {
        this.userService.addUsers(name, email);
        return Results.success();
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/user/all")
    public Result<UserVO> getAllUser() {
        return Results.success(this.userService.getAllUsers());
    }
}
