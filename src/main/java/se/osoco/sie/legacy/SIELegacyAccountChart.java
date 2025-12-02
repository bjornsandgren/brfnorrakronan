package se.osoco.sie.legacy;

import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;

import java.util.*;

public class SIELegacyAccountChart implements AccountChart {

    private final SIE sie;
    private final Map<Integer, AccountMetaData> accountMetaData;

    public SIELegacyAccountChart(SIE sie) {
        this.sie = sie;
        accountMetaData = accountMetaData();
    }


    @Override
    public List<AccountMetaData> asList() {
        return new ArrayList<>(accountMetaData.values());
    }

    @Override
    public AccountMetaData get(int number) {
        AccountMetaData account = accountMetaData.get(number);
        if (account == null) {
            throw new IllegalArgumentException("No account found for number: '" + number +"'");
        }
        return account;
    }

    @Override
    public int size() {
        return accountMetaData.size();
    }

    private Map<Integer, AccountMetaData> accountMetaData() {
        final Map<String, Konto> kontoMap = new TreeMap<>();
        for (Post post : sie.posts()) {
            switch (post.key()) {
                case KONTO -> {
                    Konto konto = new Konto(post);
                    kontoMap.put(konto.number, konto);
                }
                case KTYP -> {
                    Ktyp ktyp = new Ktyp(post);
                    Konto konto = kontoMap.get(ktyp.number);
                    konto.ktyp = ktyp;
                }
                case SRU -> {
                    Sru sru = new Sru(post);
                    Konto konto = kontoMap.get(sru.number);
                    konto.sru = sru;
                }
                case IB -> {
                    Balance ib = new Balance(post);
                    if (ib.isCurrentYear()) {
                        Konto konto = kontoMap.get(ib.acccountNumber);
                        konto.ib = ib;
                    }
                    //ignore IB for last year since we are not displaying it
                }
                case UB -> {
                    Balance ub = new Balance(post);
                    if (ub.isCurrentYear()) {
                        Konto konto = kontoMap.get(ub.acccountNumber);
                        konto.ub = ub;
                    } else if (ub.isLastYear()) {
                        Konto konto = kontoMap.get(ub.acccountNumber);
                        konto.ib = ub; //last year UB is this year IB
                    }
                    //ignore UB for last year and further back since we are not displaying them
                }
                case RES -> {
                    Balance res = new Balance(post);
                    Konto konto = kontoMap.get(res.acccountNumber);
                    if (res.isCurrentYear()) {
                        konto.res = res;
                    }
                    if (res.isLastYear()) {
                        konto.resLastYear = res;
                    }
                }
            }
        }

        final Map<Integer, AccountMetaData> accounts = new HashMap<>();
        for (Konto konto : kontoMap.values()) {
            accounts.put(Integer.valueOf(konto.number), new SieAccountMetaData(konto));
        }

        return accounts;
    }
}