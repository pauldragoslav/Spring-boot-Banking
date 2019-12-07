package com.example.paul.unit;

import com.example.paul.controllers.TransactionRestController;
import com.example.paul.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionRestController.class)
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void givenMissingInput_whenMakeTransfer_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenInvalidInput_whenMakeTransfer_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                .content("{ \"sourceAccount\": {\"sortCode\": \"53-68-92\", \"accountNumber\": \"73084635\" }, \"targetAccount\": {\"sortCode\": \"65-93-37\", \"accountNumber\": \"21956204\"}, \"amount\": -10}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenNoAccountForInput_whenMakeTransfer_thenVerifyOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                .content("{\"sourceAccount\": {\"sortCode\": \"53-68-92\", \"accountNumber\": \"73084635\"}, \"targetAccount\": {\"sortCode\": \"65-93-37\", \"accountNumber\": \"21956204\"}, \"amount\": 105.0, \"reference\": \"My ref\", \"latitude\": 66.23423423, \"longitude\": 105.234234}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
