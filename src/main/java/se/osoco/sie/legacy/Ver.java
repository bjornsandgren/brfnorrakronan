package se.osoco.sie.legacy;

import se.osoco.domain.Transaction;
import se.osoco.domain.Verification;
import se.osoco.domain.accounting.AccountChart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class Ver {
    public String serie;
    public String vernr;
    public String verdatum;
    public String vertext;

    public Ver(Post post) {
        List<String> tokens = post.rowTokens();
        serie = tokens.get(0);
        vernr = tokens.get(1);
        verdatum = tokens.get(2);
        vertext = tokens.get(3);
    }

    public Verification asVerification(List<Trans> transList, AccountChart accountChart) {
        List<Transaction> transactions = transList.stream().map(t -> t.asTransaction(accountChart)).toList();
        return new Verification(
                Integer.parseInt(vernr),
                serie,
                LocalDate.parse(verdatum, DateTimeFormatter.ofPattern("yyyyMMdd")),
                vertext,
                transactions);
    }
}
