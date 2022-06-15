package com.alkemy.ong.controller;


import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.BadRequestException;

import com.alkemy.ong.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> addMember(@Valid @RequestBody MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        MemberDto savedMember = memberService.addMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "id") Long id){
        memberService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
