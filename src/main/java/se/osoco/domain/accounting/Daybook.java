package se.osoco.domain.accounting;

import se.osoco.domain.Client;
import se.osoco.domain.Verification;
import se.osoco.domain.reports.BalanceSheet;
import se.osoco.domain.reports.IncomeStatement;
import se.osoco.sie.legacy.SIE;

import java.util.Comparator;
import java.util.List;

public class Daybook {
    /**
     * Represents the origin SIE file
     */
    private final SIE sie;

    private final Series verificationSeries;

    /**
     * Create a daybook from an SIE
     *
     * @param sie
     */
    public Daybook(SIE sie) {
        this.sie = sie;
        this.verificationSeries = new Series(sie.verifications());
    }

    public SIE sie() {
        return sie;
    }

    public List<Verification> verifications(String serie) {
        List<Verification> verifications = verificationSeries.get(serie);
        return verifications;
    }

    public List<Verification> allVerifications() {
        return verificationSeries.allVerifications();
    }

    public List<Verification> sortedByDate() {
        List<Verification> allVerifications = verificationSeries.allVerifications();
        allVerifications.sort(Comparator.comparing(Verification::date));
        return allVerifications;
    }

    public Verification verification(String serie, int number) {
        List<Verification> verifications = verifications(serie);
        for (Verification verification : verifications) {
            if (verification.number() == number) {
                return verification;
            }
        }
        throw new IllegalStateException("Verification '" + number + "' not found!");
    }

    public GeneralLedger ledger() {
        return new GeneralLedger(verificationSeries, sie.chartOfAccounts());
    }

    @Override
    public String toString() {
        return sie.toString();
    }

    public BalanceSheet balanceSheet() {
        return new BalanceSheet(this);
    }

    public IncomeStatement incomeStatement() {
        return new IncomeStatement(this);
    }

    public List<String> series() {
        return verificationSeries.seriesIds();
    }

    public Daybook add(Verification verification) {
        Daybook daybook = new Daybook(sie.add(verification));
        return daybook;
    }

    public String export() {
        return sie.serialize();
    }

    public Client client() {
        return sie.client();
    }

    public FiscalYear fiscalYear() {
        return new FiscalYear(sie);
    }

    public int size() {
        return verificationSeries.allVerifications().size();
    }
}