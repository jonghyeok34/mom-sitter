package com.company.app.users;

import javax.validation.Valid;

import com.company.app.users.model.dto.LoginRequestDto;
import com.company.app.users.model.dto.SignUpRequestDto;
import com.company.app.users.model.dto.SignUpResponseDto;
import com.company.app.users.services.UsersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(value="/users", consumes=MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    
    private final UsersService usersService;
    
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto form){
        usersService.validateSignUp(form);
        SignUpResponseDto dto =  usersService.signUp(form);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto form){

        return new ResponseEntity<>(usersService.login(form), HttpStatus.OK);
    }
}
