package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class NewsMapper {

    public News newsDTO2Entity(@NotNull NewsDTO dto) {
        News entity = new News();
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        return entity;
    }

    public NewsDTO newsEntity2DTO(@NotNull News entity) {
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        return dto;
    }
}
