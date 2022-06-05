package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.model.Slides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class SlideMapper {

    @Autowired
    OrganizationMapper organizationMapper;

    public Slides requestDto2SlidesEntity(@NotNull SlidesRequestDTO requestDTO){
        Slides entity = new Slides();
        entity.setImageUrl(requestDTO.getImageUrl());
        entity.setOrderSlides(requestDTO.getOrderSlides());
        entity.setOrganizations(requestDTO.getOrganizations());
        entity.setText(requestDTO.getText());
        return entity;
    }

    public SlidesResponseDTO entitySlides2responseDto(@NotNull Slides entity){
        SlidesResponseDTO responseDTO = new SlidesResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setImageUrl(entity.getImageUrl());
        responseDTO.setOrderSlides(entity.getOrderSlides());
        responseDTO.setText(entity.getText());
        responseDTO.setOrganizations(organizationMapper.oganizationEntity2DTO(entity.getOrganizations()));
        return responseDTO;
    }


}
