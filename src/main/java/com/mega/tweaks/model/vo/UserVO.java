package com.mega.tweaks.model.vo;

import com.mega.tweaks.model.entity.TestUser;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserVO
{
    @ApiModelProperty("用户列表")
    private List<TestUser> userList;
}
