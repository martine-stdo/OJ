package com.roger.oj.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 通用返回类
 * 用于封装 API 接口的响应信息，包含响应状态码、数据和消息。
 *
 * @param <T> 泛型参数，表示响应数据的类型
 */
@Data
public class BaseResponse<T> implements Serializable {

    // 响应状态码
    private int code;

    // 响应数据
    private T data;

    // 响应消息
    private String message;

    /**
     * 构造器1 - 带有全部参数
     *
     * @param code    响应状态码
     * @param data    响应数据
     * @param message 响应消息
     */
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * 构造器2 - 带有状态码和数据，默认消息为空字符串
     *
     * @param code 响应状态码
     * @param data 响应数据
     */
    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    /**
     * 构造器3 - 通过 ErrorCode 枚举构建错误响应
     *
     * @param errorCode 包含错误状态码和消息的 ErrorCode 枚举
     */
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
