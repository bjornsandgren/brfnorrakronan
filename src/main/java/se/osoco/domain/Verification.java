package se.osoco.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Verification {

    private final int number;
    private final String serie;
    private final LocalDate date;
    private final String text;
    private final List<Transaction> transactions = new ArrayList<>();

    public Verification(final int number, final String serie, final LocalDate date, final String text, final List<Transaction> transactions) {
        this.number = number;
        this.serie = serie;
        this.date = date;
        this.text = text;
        if (transactions != null) {
            this.transactions.addAll(transactions);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Verification that)) return false;
        return number == that.number && Objects.equals(serie, that.serie) && Objects.equals(date, that.date) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, serie, date, text, transactions);
    }

    public int number() {
        return number;
    }

    public String serie() {
        return serie;
    }

    public LocalDate date() {
        return date;
    }

    public String text() {
        return text;
    }

    public List<Transaction> transactions() {
        return Collections.unmodifiableList(transactions);
    }

    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#VER ").append(serie).append(" ").append(number).append(" ").append(date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))).append(" ").append(text).append("\n");
        stringBuilder.append("{\n");
        if (transactions() != null) {
            for (Transaction transaction : transactions()) {
                stringBuilder.append(transaction.serialize());
            }
            stringBuilder.append("}\n");
        }
        return stringBuilder.toString();
    }
}