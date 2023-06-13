package com.mega.tweaks.exception;

import com.mega.tweaks.model.vo.ResultCode;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException
{

    private final Integer code;

    public BaseException(ResultCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
