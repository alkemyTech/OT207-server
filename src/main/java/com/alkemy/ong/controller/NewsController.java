package com.alkemy.ong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<NewsDTO> create(@Valid NewsDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


}
