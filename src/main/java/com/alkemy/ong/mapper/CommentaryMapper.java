package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.model.Commentary;
import org.springframework.stereotype.Component;

@Component
public class CommentaryMapper {

    public Commentary CommentaryDTO2Entity(CommentaryDTO dto){
        Commentary entity = new Commentary();
        entity.setBody(dto.getBody());
        entity.setNewsId(dto.getNewsId());
        entity.setUserId(dto.getUserId());
        return entity;
    }

    public CommentaryDTO CommentaryEntity2DTO(Commentary entity){
        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setNewsId(entity.getNewsId());
        dto.setUserId(entity.getUserId());
        return dto;
    }
}
