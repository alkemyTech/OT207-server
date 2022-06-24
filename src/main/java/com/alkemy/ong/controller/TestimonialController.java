package com.alkemy.ong.controller;

import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.ITestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.TESTIMONIALS_URI)
public class TestimonialController {

    @Autowired
    private ITestimonialService testimonialService;

    @Operation(summary = "Add a new testimonial to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Create testimonial",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid field",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content)})
    @PostMapping
    public ResponseEntity<TestimonialDTO> createTestimonial(@Valid @RequestBody TestimonialDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.save(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update testimonial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Update testimonial",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid field",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<TestimonialDTO> updateTestimonial(@Valid @RequestBody TestimonialDTO dto, BindingResult bindingResult,
                                                            @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.update(id, dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a testimonial by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Delete the testimonial",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Testimonial not found",
                content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestimonial(@PathVariable(name = "id") Long id) {
        testimonialService.deleteTestimonial(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
