package com.mega.tweaks.model.vo;


import com.mega.tweaks.util.SpringUtils;

import lombok.Getter;

@Getter
public enum ResultCode
{

    SUCCESS(0),
    ERROR(1),
    PARAM_ERROR(4000),
    // 10000 认证相关
    // MT没有这个节点
    ERR_10000(10000),
    // 签名数据过期或不存在
    ERR_10001(10001),
    // 小狐狸签名验证失败
    ERR_10002(10002),
    // 不支持的签名类型
    ERR_10003(10003),
    // discord未绑定地址
    ERR_10004(10004),
    // 土狗绑定钱包数据超过5条
    ERR_10005(10005),
    // 解绑token不存在或不属于改地址
    ERR_10006(10006),
    // 尚未超过质押周期无法解压
    ERR_10007(10007),
    // discord绑定钱包操作超时
    ERR_10008(10008),
    // discord账号或者钱包账号已被绑定
    ERR_10009(10009),
    // 钱包不存在
    ERR_10010(10010),
    // 公售验证题目过期或不存在
    ERR_10011(10011),
    // 公售验证题目答案错误
    ERR_10012(10012),
    // 尚未进入公售期
    ERR_10013(10013),
    // p2公售阶段超售
    ERR_10014(10014),
    // p2预售阶段超售
    ERR_10015(10015),

    // 11000商城相关
    // 购买失败 商品已下架
    ERR_11000(11000),
    // 购买失败 积分不够
    ERR_11001(11001),
    // 购买失败 已售罄
    ERR_11002(11002),
    // 购买失败 超过个人购买数量限制
    ERR_11003(11003),
    // 订单数据补全失败 订单不属于当前用户
    ERR_11004(11004),
    ;

    private final Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return SpringUtils.getLocaleMessage(this.code);
    }

    public static ResultCode getExchangeCode(Integer code) {
        for (ResultCode exchangeCode : ResultCode.values()) {
            if (exchangeCode.getCode().equals(code)) {
                return exchangeCode;
            }
        }
        return null;
    }
}
