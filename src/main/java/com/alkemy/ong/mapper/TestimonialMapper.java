package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TestimonialMapper {


    public Testimonial testimonialDto2Entity(@NotNull TestimonialDto dto) {
        return new Testimonial(
                dto.getName(),
                dto.getImage(),
                dto.getContent()
        );
    }

    public TestimonialDto testimonialEntity2Dto(@NotNull Testimonial entity) {
        return new TestimonialDto(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getContent()
        );
    }
}
