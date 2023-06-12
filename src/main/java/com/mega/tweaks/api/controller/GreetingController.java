package com.mega.tweaks.api.controller;

import com.mega.tweaks.api.model.vo.GreetingVO;
import com.mega.tweaks.api.model.vo.Result;
import com.mega.tweaks.api.model.vo.Results;
import com.mega.tweaks.api.service.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试")
@RestController
public class GreetingController
{
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @ApiOperation("打招呼")
    @GetMapping("/greeting")
    public Result<GreetingVO> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return Results.success(this.greetingService.getGreeting(name));
    }

}
