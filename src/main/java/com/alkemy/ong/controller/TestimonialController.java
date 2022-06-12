package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private ITestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialDto> createTestimonial(@Valid @RequestBody TestimonialDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TestimonialDto> updateTestimonial(@Valid @RequestBody TestimonialDto dto, BindingResult bindingResult,
            @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.update(id, dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestimonial(@PathVariable(name = "id") Long id) {
        testimonialService.deleteTestimonial(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
