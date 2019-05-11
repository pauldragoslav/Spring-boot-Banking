package com.example.paul.unit;

import com.example.paul.controllers.AccountRestController;
import com.example.paul.models.Account;
import com.example.paul.services.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountRestController.class)
public class AccountRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenMissingInput_whenCheckingBalance_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void givenInvalidInput_whenCheckingBalance_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                .content("{\"sortCode\": \"53-68\",\"accountNumber\": \"78934\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void givenNoAccountForInput_whenCheckingBalance_thenVerifyNoContent() throws Exception {
        BDDMockito.given(accountService.getAccount(null, null)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                .content("{\"sortCode\": \"53-68-92\",\"accountNumber\": \"78901234\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void givenAccountDetails_whenCheckingBalance_thenVerifyOk() throws Exception {
        BDDMockito.given(accountService.getAccount(null, null)).willReturn(
                new Account(1L, "53-68-92", "78901234", 10.1, "Some Bank", "John"));

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                .content("{\"sortCode\": \"53-68-92\",\"accountNumber\": \"78901234\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
