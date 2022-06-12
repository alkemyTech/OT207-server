package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.IS3Service;
import com.alkemy.ong.dto.Base64ImageDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SlideServiceImpl implements ISlideService {

    @Autowired
    private IS3Service is3Service;
    @Autowired
    private SlidesRepository slidesRepository;
    @Autowired
    private SlideMapper slidesMapper;

    @Transactional
    @Override
    public SlidesResponseDTO save(SlidesRequestDTO requestDTO) {
        Base64ImageDTO base64ImageDto = new Base64ImageDTO(requestDTO.getBase64Value(), requestDTO.getNameImage());
        String key = is3Service.uploadBase64Image(base64ImageDto);
        requestDTO.setImageUrl(is3Service.getObjectUrl(key));
        if (requestDTO.getOrderSlides() == null || requestDTO.getOrderSlides() < 0) {
            List<Integer> orden = new ArrayList<>();
            List<Slides> slidesList = this.slidesRepository.findAll();
            for (Slides aux : slidesList) {
                orden.add(aux.getOrderSlides());
            }
            if (orden.isEmpty()) {
                requestDTO.setOrderSlides(1);
            } else {
                requestDTO.setOrderSlides(findLargerInteger(orden) + 1);
            }
        }
        Slides entity = this.slidesMapper.requestDto2SlidesEntity(requestDTO);
        return this.slidesMapper.entitySlides2responseDto(this.slidesRepository.save(entity));
    }

    @Override
    public List<SlidesResponseDTO> findAll() {
        List<Slides> entities = this.slidesRepository.findAll();
        if(entities.isEmpty()){
            throw new NotFoundException("The Slides list is empty");
        }
        List<SlidesResponseDTO> result = this.slidesMapper.entitySlidesList2responseDtoList(entities);
        result.sort(Comparator.comparing(SlidesResponseDTO::getOrderSlides));
        return result;
    }

    public int findLargerInteger(List<Integer> integers) {
        integers.sort(Collections.reverseOrder());
        return integers.get(0);
    }

}
