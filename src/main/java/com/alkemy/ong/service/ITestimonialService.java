package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import org.springframework.transaction.annotation.Transactional;

public interface ITestimonialService {
    TestimonialDto save(TestimonialDto dto);

    @Transactional
    TestimonialDto update(Long id, TestimonialDto dto);
}
