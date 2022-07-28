package com.example.paul.utils;

import com.example.paul.constants.constants;

public class InputValidator {

    public static boolean isSearchCriteriaValid(AccountInput accountInput) {
        return constants.SORT_CODE_PATTERN.matcher(accountInput.getSortCode()).find() &&
                constants.ACCOUNT_NUMBER_PATTERN.matcher(accountInput.getAccountNumber()).find();
    }

    public static boolean isAccountNoValid(String accountNo) {
        return constants.ACCOUNT_NUMBER_PATTERN.matcher(accountNo).find();
    }

    public static boolean isCreateAccountCriteriaValid(CreateAccountInput createAccountInput) {
        return (!createAccountInput.getBankName().isBlank() && !createAccountInput.getOwnerName().isBlank());
    }

    public static boolean isSearchTransactionValid(TransactionInput transactionInput) {
        // TODO Add checks for large amounts; consider past history of account holder and location of transfers

        if (!isSearchCriteriaValid(transactionInput.getSourceAccount()))
            return false;

        if (!isSearchCriteriaValid(transactionInput.getTargetAccount()))
            return false;

        if (transactionInput.getSourceAccount().equals(transactionInput.getTargetAccount()))
            return false;

        return true;
    }
}
