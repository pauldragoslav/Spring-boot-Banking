package com.example.paul.services;

import com.example.paul.models.Account;
import com.example.paul.repositories.AccountRepository;
import com.example.paul.repositories.TransactionRepository;
import com.example.paul.utils.CreateAccountInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account getAccount(String sortCode, String accountNumber) {
        Optional<Account> account = accountRepository
                .findBySortCodeAndAccountNumber(sortCode, accountNumber);

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findBySourceAccountIdOrderByInitiationDate(value.getId())));

        return account.orElse(null);
    }

    public Account createAccount(String bankName, String ownerName) {
        CreateAccountInput createAccountInput = new CreateAccountInput();
        Account newAccount = new Account(bankName, ownerName, createAccountInput.generateSortCode(), createAccountInput.generateAccountNumber(), 0.00);
        return accountRepository.save(newAccount);
    }
}
