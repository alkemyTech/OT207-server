package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.model.Commentary;
import com.alkemy.ong.service.impl.CommentaryServiceImpl;

public interface ICommentaryService{

    CommentaryDTO save(CommentaryDTO dto);

}
