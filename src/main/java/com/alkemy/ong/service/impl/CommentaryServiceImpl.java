package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.mapper.CommentaryMapper;
import com.alkemy.ong.model.Commentary;
import com.alkemy.ong.repository.CommentaryRepository;
import com.alkemy.ong.service.ICommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryServiceImpl implements ICommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private CommentaryMapper mapper;

    @Override
    public CommentaryDTO save(CommentaryDTO commentaryDTO) {
        Commentary commentary = mapper.CommentaryDTO2Entity(commentaryDTO);
        Commentary commentarySaved = commentaryRepository.save(commentary);
        return mapper.CommentaryEntity2DTO(commentarySaved);
    }
}
