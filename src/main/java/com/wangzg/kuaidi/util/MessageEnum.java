package com.wangzg.kuaidi.util;

/**
 * 存放消息代码及其类型描述
 */
public enum MessageEnum {
    SUCCESS(100, "success"),
    FAIL(200, "fail");

    // 消息代码
    private Integer code;

    // 消息类型
    private String info;

    MessageEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
