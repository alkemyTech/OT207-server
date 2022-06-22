package com.alkemy.ong.domain.service;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICommentaryService{

    CommentaryDTO save(CommentaryDTO dto);
    
    List<CommentaryBodyDTO> getCommentaries();

    void deleteById(Long id, HttpServletRequest request);

}
