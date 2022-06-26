package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.domain.service.ICategoryService;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.CategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @MockBean
    private UserDetailsCustomService detailsCustomService;

    @MockBean
    private JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @BeforeEach
    void configure() {
        objectMapper = new ObjectMapper();
    }


    private CategoryDTO createDtoEntity() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("News");
        categoryDTO.setImage("image.png");
        categoryDTO.setDescription("This is a description");
        return categoryDTO;
    }

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void testCreateCategory() throws Exception {
        CategoryDTO categoryDTO = createDtoEntity();

        given(categoryService.addCategory(any(CategoryDTO.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        String contactString = objectMapper.writeValueAsString(categoryDTO);

        ResultActions response = mockMvc.perform(post(Url.CATEGORY_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactString));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(categoryDTO.getName())))
                .andExpect(jsonPath("$.image",
                        is(categoryDTO.getImage())))
                .andExpect(jsonPath("$.description",
                        is(categoryDTO.getDescription())));
    }

}
