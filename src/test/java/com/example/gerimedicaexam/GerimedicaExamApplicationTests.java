package com.example.gerimedicaexam;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import java.io.File;
import java.io.FileInputStream;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GerimedicaExamApplicationTests {

    final private String PREFIX = "/api/v1";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void shouldReturnOkAndInitialEmptyList () throws Exception {
        getOkAndEmptyList();
    }

    @Test
    @Order(2)
    public void shouldReturnCreated () throws Exception {
        File csvFile = new File("src/main/resources/files/exercise.csv");
        FileInputStream fileInputStream = new FileInputStream(csvFile);
        MockMultipartFile file = new MockMultipartFile(
                "file",
                csvFile.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileInputStream);
        this.mockMvc.perform(multipart(PREFIX + "/upload").file(file))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnNumberOfElementsInDB () throws Exception {
        this.mockMvc.perform(get(PREFIX + "/records")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(18)));
    }

    @Test
    @Order(3)
    public void shouldReturnElement () throws Exception {
        this.mockMvc.perform(get(PREFIX + "/records/415929009")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.source").value("ZIB"))
                .andExpect(jsonPath("$.codeListCode").value("ZIB003"))
                .andExpect(jsonPath("$.code").value("415929009"))
                .andExpect(jsonPath("$.displayValue").value("Inguinale temperatuur (via de lies)"))
                .andExpect(jsonPath("$.longDescription").value(""))
                .andExpect(jsonPath("$.fromDate").value("2019-01-01"))
                .andExpect(jsonPath("$.toDate").doesNotExist())
                .andExpect(jsonPath("$.sortingPriority").doesNotExist());
    }

    @Test
    @Order(4)
    public void shouldReturnOkAndEmptyListAfterDelete() throws Exception {
        this.mockMvc.perform(delete(PREFIX + "/records")).andDo(print())
                .andExpect(status().isOk());
        getOkAndEmptyList();
    }

    private void getOkAndEmptyList () throws Exception {
        this.mockMvc.perform(get(PREFIX + "/records")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
