package com.roger.oj.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 删除请求
 * 用于封装删除操作的请求参数，包含待删除对象的 id。
 */
@Data
public class DeleteRequest implements Serializable {

    // 待删除对象的 id
    private Long id;

    // 序列化版本号，用于确保序列化和反序列化的兼容性
    private static final long serialVersionUID = 1L;
}
