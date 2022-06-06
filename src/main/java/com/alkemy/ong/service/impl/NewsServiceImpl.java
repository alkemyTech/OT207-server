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
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS = "news";
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsMapper mapper;

    @Transactional
    public NewsDTO save(NewsDTO dto){
        Category category = categoryRepository.findByName(NEWS);
        News news = mapper.newsDTO2Entity(dto);
        if (category == null){
            throw new NotFoundException("Category not found: " + NEWS);
        }
        news.setCategory(category);
        News newsSaved = newsRepository.save(news);
        return mapper.newsEntity2DTO(newsSaved);
    }

    @Transactional(readOnly = true)
    @Override
    public NewsDTO getById(Long id){
        Optional<News> entityResult = this.newsRepository.findById(id);
        if(entityResult.isEmpty()){
            throw new NotFoundException("Id not found");
        }
        return this.mapper.newsEntity2DTO(entityResult.get());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<News> entity = this.newsRepository.findById(id);
        if(entity.isEmpty()){
            throw new NotFoundException("Id not found");
        }
        this.newsRepository.delete(entity.get());
    }

}
