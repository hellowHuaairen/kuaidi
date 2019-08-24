package com.wangzg.kuaidi.util;

/**
 * 用于返回消息
 * @param <T>
 */
public class Message<T> {

    // 消息代码
    private Integer code;

    // 消息描述信息
    private String message;

    // 消息具体内容
    private T data;

    public Message() {
    }

    public Message(Integer code) {
        this.code = code;
    }

    public Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
