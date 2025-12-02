package se.osoco.domain.accounting;

import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles all verifications i all series. Uses a shallow copy of the verifications list so that a verification can
 * be added and deleted without effecting the original list.
 */
class Series {

    private final static String DEFAULT_SERIE_ID = "1";

    private final Map<String,List<Verification>> series = new HashMap<>();

    public Series(Map<String, List<Verification>> verifications) {
        for (Map.Entry<String,List<Verification>> entry : verifications.entrySet()) {
            List<Verification> copy = new ArrayList<>();
            copy.addAll(entry.getValue());
            series.put(entry.getKey(), copy);
        }
        if (series.isEmpty()) {
            series.put(DEFAULT_SERIE_ID, new ArrayList<>());
        }
    }

    public List<Verification> get(String serie) {
        return series.get(serie);
    }

    public List<String> seriesIds() {
        return new ArrayList<>(series.keySet());
    }

    Map<String, List<Transaction>> accountTransactions() {
        Map<String,List<Transaction>> accountTransactions = new HashMap<>();
        for (List<Verification> verificationList: series.values()) {
            for (Verification verification : verificationList) {
                for(Transaction verificationTransaction : verification.transactions()) {
                    String accountNumber = verificationTransaction.accountNumber();
                    List<Transaction> transactions = accountTransactions.computeIfAbsent(accountNumber, k -> new ArrayList<>());
                    transactions.add(verificationTransaction);
                }
            }
        }
        return accountTransactions;
    }

    public List<Verification> allVerifications() {
        List<Verification> allVerificatons = new ArrayList<>();
        for(List<Verification> verifications : series.values()) {
            allVerificatons.addAll(verifications);
        }
        return allVerificatons;
    }
}