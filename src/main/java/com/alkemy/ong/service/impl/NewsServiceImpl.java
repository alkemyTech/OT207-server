package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS = "news";
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsMapper mapper;
    public NewsDTO save(NewsDTO dto){
        Category category = categoryRepository.findByName(NEWS);
        News news = mapper.newsDTO2Entity(dto);
        if (category == null){
            throw new NotFoundException("Category not found: " + NEWS);
        }
        news.setCategoryId(category);
        News newsSaved = newsRepository.save(news);
        return mapper.newsEntity2DTO(newsSaved);
    }

}
