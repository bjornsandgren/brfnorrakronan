package se.osoco.sie.legacy;


import se.osoco.domain.Transaction;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;

import java.math.BigDecimal;
import java.util.ListIterator;

class Trans {

    public final String kontonr;
    public String belopp;

    private final Post post;

    public Trans(Post post) {
        this.post = post;
        String[] tokens = post.value().split("\\s+");
        kontonr = tokens[0];
        if (tokens[1].equals("{}")) {
            belopp = tokens[2];
        } else {
            for (int i=0; i<tokens.length; i++) {
                if (tokens[i].contains("}")) {
                    belopp = tokens[i + 1];
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return post.toString();
    }

    public Transaction asTransaction(AccountChart accountChart) {
        AccountMetaData accountMetaData = accountChart.get(Integer.parseInt(kontonr));
        return new Transaction(accountMetaData.number(), accountMetaData.name(), new BigDecimal(belopp));
    }
}