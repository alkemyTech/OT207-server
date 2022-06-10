package com.alkemy.ong.controller;

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
//    COMO usuario
//    QUIERO ver Slides cuando entro a la pagina
//    PARA estar más informados sobre ellos
//
//    Criterios de aceptación:
//
//    En el endpoint de datos públicos de la Organización,
//    se deberán agregar el listado de Slides ordenados por el campo "order".
//    Los Slides serán los pertenecientes a la Organización,
//    en base al campo organization_id que deberá corresponder a la Organización que se está devolviendo

}
