package com.company.app.common.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.company.app.common.exceptions.UserTokenException;
import com.company.app.common.utils.JwtTokenUtil;
import com.company.app.users.model.UserModel;
import com.company.app.users.model.UserToken;
import com.company.app.users.repository.UserModelRepository;
import com.company.app.users.repository.UserTokenRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ExtendWith(MockitoExtension.class)
public class JwtTokenServicesTests {
    @InjectMocks
    private JwtTokenServices jwtTokenServices;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private UserModelRepository userModelRepository;
    @Mock
    private UserTokenRepository userTokenRepository;

    @BeforeEach
    public void init(){
        // mock mvc & exception handler
    }

    private UserToken rightToken(){
        return UserToken.builder()
                        .userId("abcd")
                        .token("aaa")
                        .build();
    }

    private UserModel rightUser(){
        return UserModel.builder()
                        .memberNo(1L)
                        .email("abc@email.com")
                        .build();
    }

    
    @DisplayName("성공 : Token일 경우")
    @Test
    void successTokenHeader() throws Exception{
        HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
        doReturn("Token aaaa").when(mockedRequest).getHeader("Authorization");
        assertEquals("aaaa", jwtTokenServices.getTokenFromRequest(mockedRequest));
    }

    @DisplayName("실패: 토큰 앞에 Bearer를 붙일 경우")
    @Test
    void failedToken() throws Exception{
        HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
        doReturn("Bearer aaaa").when(mockedRequest).getHeader("Authorization");
        assertThrows(UserTokenException.class, ()-> jwtTokenServices.getTokenFromRequest(mockedRequest));
    }

    @DisplayName("성공: getUserModelFromToken")
    @Test
    void 테스트_getUserModelFromToken() throws Exception{
        final String token = "aaa";
        final String email = "abcd@email.com";
        final UserModel user = rightUser();
        
        doReturn(email).when(jwtTokenUtil).getUsernameFromToken(token);
        doReturn(Optional.of(user)).when(userModelRepository).findByEmail(any());
        doReturn(Optional.of(rightToken())).when(userTokenRepository).findByUserId(any());
        assertEquals(user, jwtTokenServices.getUserModelFromToken(token));
    }

    @DisplayName("실패: getUserModelFromToken - jwtTokenUtil 에러")
    @Test
    void 테스트_실패_getUserModelFromToken() throws Exception{
        final String token = "aaa";
        final String token2 = "aaa2";
        final String token3 = "aaa3";
        // argument 에러
        doThrow(new IllegalArgumentException()).when(jwtTokenUtil).getUsernameFromToken(token);
        assertThrows(UserTokenException.class, ()->jwtTokenServices.getUserModelFromToken(token));
        
        // 기한지난 jwtException
        doThrow(new ExpiredJwtException(null, null, null)).when(jwtTokenUtil).getUsernameFromToken(token2);
        assertThrows(UserTokenException.class, ()->jwtTokenServices.getUserModelFromToken(token2));
        
        // jwt:MalformedJwtException
        doThrow(new MalformedJwtException("ㅁ")).when(jwtTokenUtil).getUsernameFromToken(token3);
        assertThrows(UserTokenException.class, ()->jwtTokenServices.getUserModelFromToken(token3));
    
    
    }
}
