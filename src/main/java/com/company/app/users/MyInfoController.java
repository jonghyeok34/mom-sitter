package com.company.app.users;

import com.company.app.users.services.MyInfoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/my_info", consumes=MediaType.APPLICATION_JSON_VALUE)
public class MyInfoController {
    
    private final MyInfoService myInfoService;
    
    @GetMapping("")
    public ResponseEntity<?> myInfo(){
        return new ResponseEntity<>(myInfoService.myInfo(), HttpStatus.OK);
    }

}
