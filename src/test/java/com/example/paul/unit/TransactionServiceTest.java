package com.example.paul.unit;

import com.example.paul.models.Account;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.services.TransactionService;
import com.example.paul.utils.AccountInput;
import com.example.paul.utils.TransactionInput;
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
public class TransactionServiceTest {

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

    @Before
    public void setUp() {
        var sourceAccount = new Account(1L, "53-68-92", "78901234", 458.1, "Some Bank", "John");
        var targetAccount = new Account(2L, "67-41-18", "48573590", 64.9, "Some Other Bank", "Major");

        Mockito.when(accountRepository.findBySortCodeAndAccountNumber("53-68-92", "78901234"))
                .thenReturn(Optional.of(sourceAccount));
        Mockito.when(accountRepository.findBySortCodeAndAccountNumber("67-41-18", "48573590"))
                .thenReturn(Optional.of(targetAccount));
    }

    @Test
    public void whenTransactionDetails_thenTransferShouldBeDenied() {
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

        Assertions.assertThat(isComplete).isTrue();
    }

    @Test
    public void whenTransactionDetailsAndAmountTooLarge_thenTransferShouldBeDenied() {
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

        Assertions.assertThat(isComplete).isFalse();
    }
}
