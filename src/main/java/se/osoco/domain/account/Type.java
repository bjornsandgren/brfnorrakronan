package se.osoco.domain.account;

public class Type {
    private int value;

    public Type(int value) {
        this.value = value;
    }

    public int number() {
        return value;
    }

    public String text() {
        switch (value) {
            case 1:
                return "asset";
            case 10:
                return "intangible assets";
            default:
                throw new IllegalArgumentException("No known type: " + value);
        }
    }

}
