package com.vergilyn.sample.springboot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public abstract class AbstractSingletonTest {

    @Autowired
    MockMvc mockMvc;
    @LocalServerPort
    int serverPort;

    @SneakyThrows
    protected void assertExcepted(String application, int serverPort) {
        mockMvc.perform(get("/demo/get"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.singleton.name").value(application))
               .andExpect(jsonPath("$.singleton.port").value(serverPort));
    }
}