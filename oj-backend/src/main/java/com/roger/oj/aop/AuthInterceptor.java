package com.roger.oj.aop;

import com.roger.oj.annotation.AuthCheck;
import com.roger.oj.common.ErrorCode;
import com.roger.oj.exception.BusinessException;
import com.roger.oj.model.entity.User;
import com.roger.oj.model.enums.UserRoleEnum;
import com.roger.oj.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验 AOP
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint  切点
     * @param authCheck  权限校验注解
     * @return  切点方法的返回值
     * @throws Throwable  可能抛出的异常
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 从注解中获取必须的角色
        String mustRole = authCheck.mustRole();
        // 获取当前请求的 HttpServletRequest 对象
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);

        // 权限校验逻辑
        if (StringUtils.isNotBlank(mustRole)) {
            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
            // 如果注解中指定的角色无效，抛出权限异常
            if (mustUserRoleEnum == null) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }

            String userRole = loginUser.getUserRole();

            // 如果用户被封号，直接拒绝
            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }

            // 如果必须有管理员权限，但用户角色不是管理员，拒绝
            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
                if (!mustRole.equals(userRole)) {
                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
                }
            }
        }

        // 通过权限校验，放行执行切点方法
        return joinPoint.proceed();
    }
}
