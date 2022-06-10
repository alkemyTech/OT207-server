package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.model.Slides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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


    public SlidesDTO entity2SlidesDto (Slides entity){
        SlidesDTO slidesDTO = new SlidesDTO();
        slidesDTO.setId(entity.getId());
        slidesDTO.setImageUrl(entity.getImageUrl());
        slidesDTO.setOrderSlides(entity.getOrderSlides());
        slidesDTO.setText(entity.getText());
        return slidesDTO;

    }

    public List<SlidesDTO> entitySlidesList2SlidesDtoList(List<Slides> entities){
        List<SlidesDTO> dtos = new ArrayList<>();
        for (Slides s:entities) {
            dtos.add(this.entity2SlidesDto(s));
        }
        return dtos;
    }


}
