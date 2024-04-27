package com.roger.oj.service;

import com.roger.oj.model.dto.questionsubmit.QusetionSubmitAddRequest;
import com.roger.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roger.oj.model.entity.User;

/**
* @author 20624
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-04-27 19:31:16
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser 用户登录信息
     * @return
     */
    long doQuestionSubmit(QusetionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
