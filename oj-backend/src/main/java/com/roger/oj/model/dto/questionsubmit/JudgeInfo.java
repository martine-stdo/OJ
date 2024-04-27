package com.roger.oj.model.dto.questionsubmit;

import lombok.Data;

@Data
/**
 * 题目配置
 */
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 执行内存(KB)
     */
    private Long memory;

    /**
     * 执行时间(ms)
     */
    private Long time;
}
