package com.alkemy.ong.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.domain.service.ITestimonialService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.TestimonialDTO;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestimonialControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private ITestimonialService service;

    static final long ID = 1;

    @Test
    @Order(1)
    @WithUserDetails("test@admin.com")
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

    @Order(2)
    @Test
    @WithUserDetails("test@admin.com")
    void createTestimonial_shouldRespond400() throws Exception {

        final TestimonialDTO dto = TestimonialDTO.builder()
                .name(null)
                .image("foo.jpg")
                .content("Good luck")
                .build();

        mockMvc.perform(post(Url.TESTIMONIALS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    @WithUserDetails("test@admin.com")
    void update_shouldRespond200() throws Exception {

        final TestimonialDTO dto = TestimonialDTO.builder()
                .name("Turkoneta")
                .image("foo.jpg")
                .content("OT207")
                .build();

        mockMvc.perform(put(Url.TESTIMONIALS_URI + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @Order(4)
    @WithUserDetails("test@admin.com")
    void update_shouldRespond400() throws Exception {

        final TestimonialDTO dto = TestimonialDTO.builder()
                .name("Alan")
                .image("foo.jpg")
                .content("")
                .build();

        mockMvc.perform(put(Url.TESTIMONIALS_URI + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    @Order(5)
    @WithUserDetails("test@admin.com")
    void update_shouldRespond404() throws Exception {
        final TestimonialDTO dto = TestimonialDTO.builder()
                .name("Alan")
                .image("foo.jpg")
                .content("Hello")
                .build();

        mockMvc.perform(put(Url.TESTIMONIALS_URI + "/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(6)
    @WithUserDetails("test@admin.com")
    void deleteTestimonial_shouldRespond204() throws Exception {

        doNothing().when(service).deleteTestimonial(eq(ID));

        mockMvc.perform(delete(Url.TESTIMONIALS_URI + "/" + ID))
                .andExpect(status().isNoContent());
    }
}
