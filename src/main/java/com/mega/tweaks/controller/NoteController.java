package com.mega.tweaks.controller;

import com.mega.tweaks.model.vo.NoteVO;
import com.mega.tweaks.model.vo.Result;
import com.mega.tweaks.model.vo.Results;
import com.mega.tweaks.model.vo.UserVO;
import com.mega.tweaks.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "MongoDB 测试")
@RestController
public class NoteController
{
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ApiOperation("添加笔记")
    @PostMapping("/note/add")
    public Result<?> addNote(@RequestParam String note) {
        this.noteService.addNote(note);
        return Results.success();
    }

    @ApiOperation("获取笔记列表")
    @GetMapping("/note/all")
    public Result<NoteVO> getAllNote() {
        return Results.success(this.noteService.getAllNote());
    }
}
