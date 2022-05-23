package com.alkemy.ong.repository;

import com.alkemy.ong.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<News, Long> {

}
