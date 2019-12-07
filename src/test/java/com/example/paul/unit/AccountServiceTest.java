package com.example.paul.unit;

import com.example.paul.models.Account;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.services.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

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

    @BeforeEach
    void setUp() {
        var account = new Account(1L, "53-68-92", "78901234", 10.1, "Some Bank", "John");

        Mockito.when(accountRepository.findBySortCodeAndAccountNumber("53-68-92", "78901234"))
                .thenReturn(Optional.of(account));
    }

    @Test
    void whenAccountDetails_thenAccountShouldBeFound() {
        var account = accountService.getAccount("53-68-92", "78901234");

        Assertions.assertThat(account.getOwnerName()).isEqualTo("John");
        Assertions.assertThat(account.getSortCode()).isEqualTo("53-68-92");
        Assertions.assertThat(account.getAccountNumber()).isEqualTo("78901234");
    }
}
