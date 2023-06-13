package com.mega.tweaks.model.vo;

import com.mega.tweaks.exception.BaseException;

public class Results
{

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result<?> success() {
        return success(null);
    }

    public static Result<?> error(ResultCode resultCode) {
        return error(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static Result<?> error(BaseException e) {
        return error(e.getCode(), e.getMessage(), null);
    }
}
