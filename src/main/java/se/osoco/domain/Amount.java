package se.osoco.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Amount {
    private final BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }

    public String formatted() {
        return formatted(this.value);
    }

    public String rounded() {
        BigDecimal scaled = value.setScale(0, RoundingMode.HALF_UP);
        return formatted(scaled);
    }

    private static  String formatted(BigDecimal value) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("sv", "SE"));
        return numberFormat.format(value);
    }

    @Override
    public String toString() {
        return rounded().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount amount)) return false;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Amount negate() {
        return new Amount(value.negate());
    }
}