package se.osoco.domain;


import org.springframework.util.StringUtils;

import java.util.Objects;

public class OrganisationNumber {
    private String value;

    public OrganisationNumber(final String value) {
        if (!StringUtils.hasText(value)) {
            throw new RuntimeException("value is blank");
        }
        this.value = value.replace("\"", "");
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganisationNumber that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
