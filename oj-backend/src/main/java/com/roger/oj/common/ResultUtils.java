package com.roger.oj.common;

/**
 * 返回工具类
 * 提供快速创建响应对象的方法，用于统一处理接口返回结果。
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T>  响应数据的类型
     * @return 包含成功状态码、数据和消息的响应对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 包含错误状态码和消息的 ErrorCode 枚举
     * @return 包含错误状态码、null 数据和错误消息的响应对象
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code    错误状态码
     * @param message 错误消息
     * @return 包含指定错误状态码、null 数据和错误消息的响应对象
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 包含错误状态码和消息的 ErrorCode 枚举
     * @param message   错误消息
     * @return 包含指定错误状态码、null 数据和错误消息的响应对象
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }
}
