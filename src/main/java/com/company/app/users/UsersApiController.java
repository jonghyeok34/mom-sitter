package com.company.app.users;

import com.company.app.users.services.UsersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api/users", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UsersApiController {
    
    private final UsersService usersService;

    @GetMapping("/my_info")
    public ResponseEntity<?> myInfo(){
        return new ResponseEntity<>(usersService.myInfo(), HttpStatus.OK);
    }
}
