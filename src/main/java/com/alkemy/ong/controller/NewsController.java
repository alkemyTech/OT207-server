package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @PostMapping
    public ResponseEntity<NewsDTO> create(@Valid @RequestBody NewsDTO dto, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        throw new BadRequestException(bindingResult);
    }
    NewsDTO result = newsService.save(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable("id") Long id){
        NewsDTO newsDTO = this.newsService.getById(id);
        return ResponseEntity.ok().body(newsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> putById(@PathVariable("id") Long id,
                                           @Valid @RequestBody NewsDTO dto){
        NewsDTO newsDTO = this.newsService.putById(id, dto);
        return ResponseEntity.ok().body(newsDTO);
    }
}
