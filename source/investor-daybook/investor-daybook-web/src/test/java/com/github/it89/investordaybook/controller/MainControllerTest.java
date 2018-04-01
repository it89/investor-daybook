package com.github.it89.investordaybook.controller;

/*import com.github.it89.investordaybook.config.AppConfig;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;*/
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)*/
public class MainControllerTest {
    @Test
    @Disabled("Not implement")
    public void doNothing() throws Exception {
    }

    // TODO: Write for JUnit5
    /*@Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void checkIsMapped() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void checkDoPostIsMapped() throws Exception {
        mockMvc.perform(post("/doPost")).andExpect(status().isOk()).andExpect(content().string("post!"));
    }*/
}