package com.company.app.users;

import javax.validation.Valid;

import com.company.app.users.model.dto.AddParentTypeRequestDto;
import com.company.app.users.model.dto.AddSitterTypeRequestDto;
import com.company.app.users.services.MyInfoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/my_info", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MyInfoController {

    private final MyInfoService myInfoService;

    @GetMapping("")
    public ResponseEntity<?> myInfo() {
        return new ResponseEntity<>(myInfoService.myInfo(), HttpStatus.OK);
    }

    @PatchMapping("/user_type/parent")
    public ResponseEntity<?> addParentType(@Valid @RequestBody AddParentTypeRequestDto form) {
        return new ResponseEntity<>(myInfoService.addParentType(form), HttpStatus.OK);
    }
    
    @PatchMapping("/user_type/sitter")
    public ResponseEntity<?> addSitterType(@Valid @RequestBody AddSitterTypeRequestDto form) {
        return new ResponseEntity<>(myInfoService.addSitterType(form), HttpStatus.OK);
    }
}
