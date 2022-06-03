package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.IS3Service;
import com.alkemy.ong.dto.Base64ImageDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
public class SlidesController {

    @Autowired
    private IS3Service IS3Service;

    @PostMapping
    public ResponseEntity<SlidesResponseDTO> createSlides(@RequestBody SlidesRequestDTO requestDTO){

        SlidesResponseDTO responseDTO = new SlidesResponseDTO();
        Base64ImageDTO base64ImageDto = new Base64ImageDTO(requestDTO.getBase64Value(), "alexis1");
        String key = IS3Service.uploadBase64Image(base64ImageDto);
        responseDTO.setImageUrl(IS3Service.getObjectUrl(key));

        return ResponseEntity.ok().body(responseDTO);

    }

}
