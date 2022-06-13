package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;

import java.util.List;

public interface ISlideService {

    SlidesResponseDTO save(SlidesRequestDTO requestDTO);

    List<SlidesDTO> findAll();
}
