package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TestimonialMapper {


    public Testimonial testimonialDto2Entity(@NotNull TestimonialDto dto) {
        Testimonial entityResult = new Testimonial();
        this.entityTestimonialRefreshValues(entityResult,dto);
        return entityResult;
    }

    public TestimonialDto testimonialEntity2Dto(@NotNull Testimonial entity) {
        return new TestimonialDto(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getContent()
        );
    }

    public void entityTestimonialRefreshValues(Testimonial entity, TestimonialDto dto){
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
    }
}
