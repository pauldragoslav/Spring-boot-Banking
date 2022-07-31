package com.example.paul.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class DepositInput {

    @NotBlank(message = "Target account no is mandatory")
    private String targetAccountNo;

    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Positive(message = "Transfer amount must be positive")
    private double amount;

    public DepositInput() {
    }

    public String getTargetAccountNo() {
        return targetAccountNo;
    }

    public void setTargetAccountNo(String targetAccountNo) {
        this.targetAccountNo = targetAccountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DepositInput{" +
                "targetAccountNo='" + targetAccountNo + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositInput that = (DepositInput) o;
        return Objects.equals(targetAccountNo, that.targetAccountNo) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetAccountNo, amount);
    }
}
