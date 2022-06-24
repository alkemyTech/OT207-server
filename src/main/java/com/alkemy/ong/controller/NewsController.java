package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.INewsService;
import com.alkemy.ong.service.INewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Operation(summary = "Add a new news to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create news",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<NewsDTO> create(@Valid @RequestBody NewsDTO dto, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        throw new BadRequestException(bindingResult);
    }
    NewsDTO result = newsService.save(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Get a news by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the news",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable("id") Long id){
        NewsDTO newsDTO = this.newsService.getById(id);
        return ResponseEntity.ok().body(newsDTO);
    }

    @Operation(summary = "Delete a news by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the news",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        this.newsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Update news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful news update",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNewsById(@PathVariable("id") Long id,
                                           @Valid @RequestBody NewsDTO dto, BindingResult bindingResult){
     if (bindingResult.hasErrors()){
         throw new BadRequestException(bindingResult);
     }
        NewsDTO newsDTO = this.newsService.updateNewsById(id, dto);
        return ResponseEntity.ok().body(newsDTO);
    }

    @Operation(summary = "Get all news for paginated user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all news",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "The list is empty",
            content = @Content)})
    @GetMapping("/page")
    public ResponseEntity<PageDTO> getAllNewsPageable(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        PageDTO<NewsDTO> newsDTOPageDTO = newsService.getAllNewsPageable(page);
        return ResponseEntity.ok().body(newsDTOPageDTO);
    }

}
