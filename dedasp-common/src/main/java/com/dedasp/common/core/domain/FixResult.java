package com.dedasp.common.core.domain;

import com.dedasp.common.enums.ResponseCode;

import java.io.Serializable;

public class FixResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 封装统一返回值
    private int code; // 响应码
    private String msg; // 响应信息
    private T data; // 数据

    // 构造方法
    public FixResult() {}

    public FixResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功返回结果
    public static <T> FixResult<T> success(ResponseCode responseCode) {
        return new FixResult<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> FixResult<T> success(T data) {
        return new FixResult<>(200, "Success", data);
    }

    public static <T> FixResult<T> success(String msg, T data) {
        return new FixResult<>(200, msg, data);
    }

    // 失败返回结果
    public static <T> FixResult<T> error(ResponseCode responseCode) {
        return new FixResult<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> FixResult<T> error(int code, String msg) {
        return new FixResult<>(code, msg, null);
    }

    public static <T> FixResult<T> error(String msg) {
        return new FixResult<>(500, msg, null);
    }

    // 自定义返回结果
    public static <T> FixResult<T> custom(int code, String msg, T data) {
        return new FixResult<>(code, msg, data);
    }

    // Getter and Setter methods
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FixResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
