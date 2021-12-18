package com.company.app.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.company.app.common.services.JwtTokenServices;
import com.company.app.users.model.UserModel;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerAuthenticationCheckAop {

    private static final String [] authenticatedMethodUrls = {"GET /api/my_info", "PATCH /api/my_info/user_type/parent", "PATCH /api/my_info/user_type/sitter"};
    private final JwtTokenServices jwtTokenServices;

    @Pointcut("execution(* com.company.app.**.**.*(..))")
    public void controllerPointCut(){
    }

    @Around("controllerPointCut()")
    public Object authenticate(ProceedingJoinPoint jointPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        final String method = request.getMethod();
        final String requestUri = request.getRequestURI();
        final String methodUrl = String.format("%s %s", method, requestUri);
        
        log.info("{}",methodUrl);

        final AntPathMatcher pathMatcher = new AntPathMatcher();

        boolean isAutheticatedMethodUrls = Arrays.stream(authenticatedMethodUrls).anyMatch((pattern) -> pathMatcher.match(pattern, methodUrl) );
        if(isAutheticatedMethodUrls){

            final String token = jwtTokenServices.getTokenFromRequest(request);
            final UserModel user = jwtTokenServices.getUserModelFromToken(token);
            
            request.setAttribute("userInfo", user);
            
        }
        Object result = jointPoint.proceed();
        return result;
    }

    


}
