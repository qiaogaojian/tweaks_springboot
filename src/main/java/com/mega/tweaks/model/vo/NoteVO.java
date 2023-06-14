package com.mega.tweaks.model.vo;

import com.mega.tweaks.mongodb.NoteEntity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class NoteVO
{
    @ApiModelProperty("笔记列表")
    private List<NoteEntity> noteList;
}
