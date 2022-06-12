package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;

public interface ISlideService {

    SlidesResponseDTO save(SlidesRequestDTO requestDTO);

    SlidesResponseDTO getSlidesById(Long id);
}
