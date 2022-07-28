package com.example.paul.utils;

import com.example.paul.constants.constants;
import com.mifmif.common.regex.Generex;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static com.example.paul.constants.constants.ACCOUNT_NUMBER_PATTERN_STRING;
import static com.example.paul.constants.constants.SORT_CODE_PATTERN_STRING;

public class CreateAccountInput {

    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @NotBlank(message = "Owner name is mandatory")
    private String ownerName;


    public CreateAccountInput() {}

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "CreateAccountInput{" +
                "bankName='" + bankName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountInput that = (CreateAccountInput) o;
        return Objects.equals(bankName, that.bankName) &&
                Objects.equals(ownerName, that.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, ownerName);
    }
}
