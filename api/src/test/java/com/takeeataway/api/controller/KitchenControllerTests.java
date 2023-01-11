package com.takeeataway.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeeataway.api.entity.Kitchen;
import com.takeeataway.api.service.KitchenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(KitchenController.class)
public class KitchenControllerTests {

    @MockBean
    KitchenService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateKitchenShouldReturnHttp201Created() throws Exception {
        Kitchen kitchen = new Kitchen("kitchen");

        Mockito.when(service.createKitchen(Mockito.any(Kitchen.class))).thenReturn(kitchen);

        mockMvc.perform(post("/kitchens")
                        .content(objectMapper.writeValueAsString(kitchen))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateKitchenShouldReturnLocationHeader() throws Exception {
        Kitchen kitchen = new Kitchen("kitchen");
        kitchen.setId(1L);

        Mockito.when(service.createKitchen(Mockito.any(Kitchen.class))).thenReturn(kitchen);

        mockMvc.perform(post("/kitchens")
                        .content(objectMapper.writeValueAsString(kitchen))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Location"))
                .andExpect(redirectedUrlPattern("http://*/kitchens/1"));
    }
}
