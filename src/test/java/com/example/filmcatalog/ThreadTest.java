package com.example.filmcatalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ThreadTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void threeThreadCount() throws Exception {
        mvc.perform(get("/counter/multithreaded"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(3000000)));
    }
}
