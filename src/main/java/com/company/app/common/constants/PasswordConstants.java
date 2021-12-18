package com.company.app.common.constants;

public class PasswordConstants {
    /**
     *  1개의 숫자  [0-9].
        적어도 1개의 소문자 알파벳 [a-z].
        적어도 1개의 대문자 알파벳 [A-Z].
        적어도 1개의 특수문자 ! @ # & ( ).
        최소 8개에서 최대 20개 글자.
     * 
     */
    public final static String PASSWORD_CHECK_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public final static String PASSWORD_CHECK_MESSAGE = "1개의 숫자  [0-9].\n적어도 1개의 소문자 알파벳 [a-z].\n적어도 1개의 대문자 알파벳 [A-Z].\n적어도 1개의 특수문자 ! @ # & ( ).\n최소 8개에서 최대 20개 글자.";
    
}
