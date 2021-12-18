package com.company.app.common.services;

import javax.servlet.http.HttpServletRequest;

import com.company.app.common.exceptions.UserTokenException;
import com.company.app.common.utils.JwtTokenUtil;
import com.company.app.users.model.UserModel;
import com.company.app.users.model.UserToken;
import com.company.app.users.repository.UserModelRepository;
import com.company.app.users.repository.UserTokenRepository;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenServices {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserModelRepository userModelRepository;
    private final UserTokenRepository userTokenRepository;
    private final static String USER_TOKEN_EXCEPTION_MSG = "로그인이 필요합니다";
    public String getTokenFromRequest(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");
        log.info("Authorization:{}", requestTokenHeader);
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Token ")) {
            jwtToken = requestTokenHeader.substring(6);
            
        } else {
            
            log.warn("JWT Token does not begin with Token String");
            throw new UserTokenException("Token does not starts with Token");
        }
        return jwtToken;
    }

    public UserModel getUserModelFromToken(final String jwtToken ){

        UserModel user = null;
        if (jwtToken != null) {
            try {
                String userId = jwtTokenUtil.getUsernameFromToken(jwtToken);
                UserToken savedToken = userTokenRepository.findById(userId).orElseThrow(()-> new UserTokenException(USER_TOKEN_EXCEPTION_MSG+"1"));
                // if(savedToken == null) {
                //     System.out.println("here1");
                //     throw new UserTokenException(USER_TOKEN_EXCEPTION_MSG);
                // }
                if(!jwtToken.equals(savedToken.getToken())){
                    System.out.println("here2");
                    throw new UserTokenException(USER_TOKEN_EXCEPTION_MSG);
                }
                user = userModelRepository.findByUserId(userId);
                return user;
                
            } catch (IllegalArgumentException | MalformedJwtException e) {
                System.out.println("Unable to get JWT Token");
                throw new UserTokenException("WRONG ERROR");
                
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                throw new UserTokenException(USER_TOKEN_EXCEPTION_MSG);
            }
            
            
        } else {
            log.warn("JWT Token does not begin with Token String");
        }
        return user;
    }

}
