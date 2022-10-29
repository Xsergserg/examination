package com.example.gerimedicaexam;

import com.example.gerimedicaexam.controller.RecordsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private RecordsController recordsController;

    @Test
    public void contextLoads() {
        assertThat(recordsController).isNotNull();
    }
}