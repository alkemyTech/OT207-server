package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.service.IActivityService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        ActivityDTO resultDTO = activityService.save(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@Valid @RequestBody ActivityDTO activityDTO, BindingResult bindingResult,
                                                      @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        ActivityDTO resultDTO = activityService.update(id, activityDTO);
        return ResponseEntity.ok().body(resultDTO);
    }

//    Descripción
//    COMO usuario administrador
//    QUIERO actualizar una actividad existente
//    PARA mantener la información actualizada
//    Criterios de aceptación:
//    PUT /activities/:id - Deberá validar que la actividad exista en base al id enviado por parámetro.
//    En el caso de que no exista devolver un error, caso contrario actualizarla y devolverla con los datos actualizados

}
