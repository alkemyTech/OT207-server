package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.Commentary;
import com.alkemy.ong.repository.CommentaryRepository;
import com.alkemy.ong.service.ICommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryServiceImpl implements ICommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Override
    public Commentary save() {
        return null;
    }
}
