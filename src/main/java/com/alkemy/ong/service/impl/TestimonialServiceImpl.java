package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper mapper;

    @Override
    @Transactional
    public TestimonialDto save(TestimonialDto dto) {
        Testimonial testimonial = mapper.testimonialDto2Entity(dto);
        return mapper.testimonialEntity2Dto(testimonialRepository.save(testimonial));
    }
}