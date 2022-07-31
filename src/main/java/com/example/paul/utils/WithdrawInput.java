package com.example.paul.utils;

import javax.validation.constraints.Positive;
import java.util.Objects;

public class WithdrawInput extends AccountInput{
    String sortCode;
    String accountNumber;

    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Positive(message = "Transfer amount must be positive")
    private double amount;

    public WithdrawInput() {
        this.sortCode = super.getSortCode();
        this.accountNumber = super.getAccountNumber();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountInput{" +
                "sortCode='" + sortCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawInput that = (WithdrawInput) o;
        return Objects.equals(sortCode, that.sortCode) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortCode, accountNumber, amount);
    }
}
