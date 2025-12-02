package se.osoco.domain;

import java.util.ArrayList;
import java.util.List;

public class Verifications {

    public final static String DEFAULT_NUMBER_SERIE = "1";

    private final String id;
    private List<Verification> verifications = new ArrayList<>();

    public Verifications() {
        this(DEFAULT_NUMBER_SERIE, new ArrayList<>());
    }

    public Verifications(final String id, List<Verification> verifications) {
        this.id = id;
        this.verifications.addAll(verifications);
    }

    public String id() {
        return id;
    }

    public Verification number(int verificationNumber) {
        return verifications.stream().filter(verification -> verification.number() == verificationNumber).findFirst().get();
    }

    public int size() {
        return verifications.size();
    }

    public List<Verification> list() {
        return verifications;
    }
}
