package com.example.paul.unit;

import com.example.paul.models.Account;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.services.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        var account = new Account(1L, "53-68-92", "78901234", 10.1, "Some Bank", "John");

        Mockito.when(accountRepository.findBySortCodeAndAccountNumber("53-68-92", "78901234"))
                .thenReturn(Optional.of(account));
    }

    @Test
    public void whenAccountDetails_thenAccountShouldBeFound() {
        var account = accountService.getAccount("53-68-92", "78901234");

        Assertions.assertThat(account.getOwnerName()).isEqualTo("John");
        Assertions.assertThat(account.getSortCode()).isEqualTo("53-68-92");
        Assertions.assertThat(account.getAccountNumber()).isEqualTo("78901234");
    }
}
