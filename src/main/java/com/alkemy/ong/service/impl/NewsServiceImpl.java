package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.INewsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS = "news";
    private static final String ID_NOT_FOUND = "Id not found: ";
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsMapper mapper;

    public NewsDTO save(NewsDTO dto) {
        Category category = getCategoryNews();
        News news = mapper.newsDTO2Entity(dto);
        news.setCategory(category);
        News newsSaved = newsRepository.save(news);
        return mapper.newsEntity2DTO(newsSaved);
    }


    @Override
    public NewsDTO getById(Long id){
        Optional<News> entityResult = this.newsRepository.findById(id);
        if(entityResult.isEmpty()){
            throw new NotFoundException(ID_NOT_FOUND);
        }
        return this.mapper.newsEntity2DTO(entityResult.get());
    }

    @Override
    public NewsDTO updateNewsById(Long id, NewsDTO dto) {
        Category category = getCategoryNews();
        News newsEntity = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        mapper.newsDTO2EntityWithId(newsEntity, dto);
        newsEntity.setCategory(category);
        return mapper.newsEntity2DTO(newsRepository.save(newsEntity));
    }

    @NotNull
    private Category getCategoryNews() {
        Category category = categoryRepository.findByName(NEWS);
        if (category == null) {
            throw new NotFoundException("Category not found: " + NEWS);
        }
        return category;
    }

}
