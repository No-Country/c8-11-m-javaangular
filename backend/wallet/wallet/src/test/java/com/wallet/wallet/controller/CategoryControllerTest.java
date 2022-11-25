package com.wallet.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.wallet.api.controller.CategoryController;
import com.wallet.wallet.api.service.impl.CategoryServiceImpl;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = { CategoryController.class })
@ContextConfiguration(classes = { CategoryController.class })
class CategoryControllerTest {

    private String uri = "/categories";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    static CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
    static CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

    @BeforeAll
    static void beforeAll(){
        categoryRequestDto.setId(1L);
        categoryRequestDto.setName("salidas");
        categoryRequestDto.setIcon("out");
        categoryRequestDto.setColorCode("#4FD23j");
        categoryRequestDto.setUserIdCreate(1L);
        categoryRequestDto.setIsDefault(true);
        categoryRequestDto.setDeleted(false);

        categoryResponseDto.setId(1L);
        categoryResponseDto.setName("salidas");
        categoryResponseDto.setIcon("out");
        categoryResponseDto.setColorCode("#4FD23j");
        categoryResponseDto.setUserIdCreate(1L);
        categoryResponseDto.setIsDefault(true);
    }

    @Test
    //@WithMockitoUser(roles = {"ROLE_ADMIN"})
    void save() throws Exception {
        when(categoryService.save(categoryRequestDto)).thenReturn(categoryResponseDto);

        mockMvc.perform( MockMvcRequestBuilders
                        .post(uri + "/save")
                        .content(objectMapper.writeValueAsString(categoryRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
    }

    @Test
    //@WithMockUser(roles = {"ROLE_ADMIN"})
    void getOne() throws Exception {
        when(categoryService.getById(1L)).thenReturn(categoryResponseDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri + "/getById/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(1))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.name").isNotEmpty())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.icon").isNotEmpty())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.colorCode").isNotEmpty())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.userIdCreate").isNumber());
        verify(categoryService, times(1)).getById(1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri + "/getById/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").doesNotExist());
        verify(categoryService, times(1)).getById(2L);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri + "/getById/{id}", "test")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }
}