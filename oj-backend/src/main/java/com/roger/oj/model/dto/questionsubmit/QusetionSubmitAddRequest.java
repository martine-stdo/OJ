package com.roger.oj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 *

 */
@Data
public class QusetionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户提交代码
     */
    private String code;


    /**
     * 题目id
     */
    private Long questionId;



    private static final long serialVersionUID = 1L;
}