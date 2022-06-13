package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.service.ICommentaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentary(@PathVariable(name = "id") Long id, HttpServletRequest request){
        commentaryService.deleteById(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
