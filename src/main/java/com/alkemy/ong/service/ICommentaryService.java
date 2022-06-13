package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import java.util.List;

public interface ICommentaryService{

    CommentaryDTO save(CommentaryDTO dto);
    
    List<CommentaryBodyDTO> getCommentaries();

}
