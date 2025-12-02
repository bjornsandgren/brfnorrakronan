package se.osoco.domain;

import java.math.BigDecimal;

public record Transaction(String accountNumber, String accountName, BigDecimal amount) {

    public String serialize() {
        return "\t#TRANS " + accountNumber() + " {} " + amount() + "\n";
    }
}