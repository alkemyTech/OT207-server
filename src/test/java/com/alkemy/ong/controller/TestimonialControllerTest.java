package com.alkemy.ong.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alkemy.ong.domain.service.ITestimonialService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.TestimonialDTO;

@ExtendWith(MockitoExtension.class)
class TestimonialControllerTest {

    private MockMvc mockMvc;

    private final long ID = 0;
    
    @InjectMocks
    private TestimonialController controller;

    @Mock
    private ITestimonialService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(Exception.class)
                .build();
    }
    
    @Test
    void update_shouldRespond201() throws Exception {
    	
    	final TestimonialDTO dto = TestimonialDTO.builder()
                .name("Alan")
                .image("foo.jpg")
                .content("Hello")
                .build();
        
        mockMvc.perform(put(Url.TESTIMONIALS_URI + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(201))
                .andReturn();
    }
    
    @Test
    void createTestimonial_shouldRespond201() throws Exception {

        final TestimonialDTO dto = TestimonialDTO.builder()
                .name("Alan")
                .image("foo.jpg")
                .content("Hello")
                .build();

        mockMvc.perform(post(Url.TESTIMONIALS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                		.andExpect(status().isCreated());
    }
    
    @Test
    void deleteTestimonial_shouldRespond204() throws Exception {

        doNothing().when(service).deleteTestimonial(eq(ID));

        mockMvc.perform(delete(Url.TESTIMONIALS_URI + "/" + ID))
                .andExpect(status().isNoContent());
    }
}
