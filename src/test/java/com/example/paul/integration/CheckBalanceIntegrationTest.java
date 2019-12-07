package com.example.paul.integration;

import com.example.paul.controllers.AccountRestController;
import com.example.paul.models.Account;
import com.example.paul.utils.AccountInput;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class CheckBalanceIntegrationTest {

    @Autowired
    private AccountRestController accountRestController;

    @Test
    void givenAccountDetails_whenCheckingBalance_thenVerifyAccountCorrect() {
        // given
        var input = new AccountInput();
        input.setSortCode("53-68-92");
        input.setAccountNumber("73084635");

        // when
        var body = accountRestController.checkAccountBalance(input).getBody();

        // then
        var account = (Account) body;
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getOwnerName()).isEqualTo("Paul Dragoslav");
        Assertions.assertThat(account.getSortCode()).isEqualTo("53-68-92");
        Assertions.assertThat(account.getAccountNumber()).isEqualTo("73084635");
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(2);
    }
}
