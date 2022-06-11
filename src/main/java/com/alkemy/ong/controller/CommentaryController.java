package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.service.ICommentaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentaryController {
    
    @Autowired
    private ICommentaryService commentaryService;
    
    @GetMapping
    public ResponseEntity<List<CommentaryBodyDTO>> getComments () {
        List<CommentaryBodyDTO> listDto = commentaryService.getCommentaries();
        return ResponseEntity.ok().body(listDto);
    }
}
