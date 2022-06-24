package com.alkemy.ong.controller;

import com.alkemy.ong.domain.service.ICommentaryService;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.service.impl.CommentaryServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Url.COMMENTS_URI)
public class CommentaryController {
    
    @Autowired
    private ICommentaryService commentaryService;

    @GetMapping
    public ResponseEntity<List<CommentaryBodyDTO>> getComments() {
        List<CommentaryBodyDTO> listDto = commentaryService.getCommentaries();
        return ResponseEntity.ok().body(listDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentaryBodyDTO> updateCommentary(@Valid @RequestBody CommentaryBodyDTO dto, BindingResult bindingResult,
                                                              @PathVariable Long id, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(commentaryService.update(id, dto, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentary(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        commentaryService.deleteById(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping()
    public ResponseEntity<CommentaryDTO> createCommentary(@RequestBody @Valid CommentaryDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        return ResponseEntity.ok(commentaryService.save(dto));
    }

}
