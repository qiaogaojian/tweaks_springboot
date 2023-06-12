package com.mega.tweaks.api.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class GreetingVO
{
    private long   id;
    private String name;
}
