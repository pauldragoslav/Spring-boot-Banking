package com.example.paul.unit;

import com.example.paul.models.Account;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.services.TransactionService;
import com.example.paul.utils.AccountInput;
import com.example.paul.utils.TransactionInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @TestConfiguration
    static class TransactionServiceTestContextConfiguration {

        @Bean
        public TransactionService transactionService() {
            return new TransactionService();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        var sourceAccount = new Account(1L, "53-68-92", "78901234", 458.1, "Some Bank", "John");
        var targetAccount = new Account(2L, "67-41-18", "48573590", 64.9, "Some Other Bank", "Major");

        when(accountRepository.findBySortCodeAndAccountNumber("53-68-92", "78901234"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findBySortCodeAndAccountNumber("67-41-18", "48573590"))
                .thenReturn(Optional.of(targetAccount));
    }

    @Test
    void whenTransactionDetails_thenTransferShouldBeDenied() {
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("78901234");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("67-41-18");
        targetAccount.setAccountNumber("48573590");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(50);
        input.setReference("My reference");

        boolean isComplete = transactionService.makeTransfer(input);

        assertThat(isComplete).isTrue();
    }

    @Test
    void whenTransactionDetailsAndAmountTooLarge_thenTransferShouldBeDenied() {
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("78901234");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("67-41-18");
        targetAccount.setAccountNumber("48573590");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(10000);
        input.setReference("My reference");

        boolean isComplete = transactionService.makeTransfer(input);

        assertThat(isComplete).isFalse();
    }
}
