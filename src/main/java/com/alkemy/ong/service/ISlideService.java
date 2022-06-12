package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;

public interface ISlideService {

    SlidesResponseDTO save(SlidesRequestDTO requestDTO);

    void deleteById(Long id);
}
