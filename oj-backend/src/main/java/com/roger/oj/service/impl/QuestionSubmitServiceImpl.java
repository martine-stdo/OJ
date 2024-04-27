package com.roger.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roger.oj.common.ErrorCode;
import com.roger.oj.exception.BusinessException;
import com.roger.oj.model.dto.questionsubmit.QusetionSubmitAddRequest;
import com.roger.oj.model.entity.Question;
import com.roger.oj.model.entity.QuestionSubmit;
import com.roger.oj.model.entity.QuestionSubmit;
import com.roger.oj.model.entity.User;
import com.roger.oj.model.enums.QuestionSubmitLanguageEnum;
import com.roger.oj.model.enums.QuestionSubmitStatusEnum;
import com.roger.oj.service.QuestionService;
import com.roger.oj.service.QuestionSubmitService;
import com.roger.oj.service.QuestionSubmitService;
import com.roger.oj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 20624
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
 * @createDate 2024-04-27 19:31:16
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QusetionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //  编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);

        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }

        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体

        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已题目提交
        long userId = loginUser.getId();
        // 每个用户串行题目提交
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        return questionSubmit.getId();
    }


}




