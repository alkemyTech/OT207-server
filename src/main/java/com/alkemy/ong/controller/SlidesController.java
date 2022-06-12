package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
public class SlidesController {

    @Autowired
    private ISlideService iSlideService;

    @PostMapping
    public ResponseEntity<SlidesResponseDTO> createSlides(@RequestBody SlidesRequestDTO requestDTO){
        SlidesResponseDTO result = this.iSlideService.save(requestDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlidesResponseDTO> getSlidesResponseDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iSlideService.getSlidesById(id));

    }

}
