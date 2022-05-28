package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.model.Commentary;
import org.springframework.stereotype.Component;

@Component
public class CommentaryMapper {

    public Commentary CommentaryDTO2Entity(CommentaryDTO dto){
        Commentary entity = new Commentary();
        entity.setBody(dto.getBody());
        entity.setNews(dto.getNews());
        entity.setUser(dto.getUser());
        return entity;
    }

    public CommentaryDTO CommentaryEntity2DTO(Commentary entity){
        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setNews(entity.getNews());
        dto.setUser(entity.getUser());
        return dto;
    }
}
