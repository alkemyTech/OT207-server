package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.model.Commentary;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.CommentaryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentaryMapper {

    private CommentaryRepository commentaryRepository;

    public Commentary commentaryDTO2Entity(CommentaryDTO dto) {
        Commentary entity = new Commentary();
        entity.setBody(dto.getBody());
        entity.setNews(dto.getNews());
        entity.setUserEntity(dto.getUserEntity());
        return entity;
    }

    public CommentaryDTO commentaryEntity2DTO(Commentary entity) {
        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setNews(entity.getNews());
        dto.setUserEntity(entity.getUserEntity());
        return dto;
    }

    public CommentaryBodyDTO commentaryEntityBodyToDTO(Commentary entity) {
        CommentaryBodyDTO dto = new CommentaryBodyDTO();
        dto.setBody(entity.getBody());
        return dto;
    }

    public List<CommentaryBodyDTO> entityListToDtoList(List<Commentary> entityList) {        
        List<CommentaryBodyDTO> DtoList = new ArrayList();
        entityList.forEach(entity -> DtoList.add(commentaryEntityBodyToDTO(entity)));
        return DtoList;
    }

    public void entityCommentaryRefreshValues(Commentary entity, CommentaryBodyDTO dto){
        entity.setBody(dto.getBody());
    }
}
