package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slides")
public class SlidesController {

    @Autowired
    private ISlideService iSlideService;

    @GetMapping
    public ResponseEntity<List<SlidesResponseDTO>> getSlides(){
        List<SlidesResponseDTO> result = this.iSlideService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<SlidesResponseDTO> createSlides(@RequestBody SlidesRequestDTO requestDTO){
        SlidesResponseDTO result = this.iSlideService.save(requestDTO);
        return ResponseEntity.ok().body(result);
    }



}
