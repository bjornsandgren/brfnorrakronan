package se.osoco.domain.account;


import se.osoco.domain.reports.AccountGroupBalance;

import java.util.Arrays;
import java.util.stream.Stream;

public enum AccountGroup {
    INTANGIBLE_FIXED_ASSETS("Immateriella anläggningstillgångar", 10,11),
    TANGIBLE_FIXED_ASSETS("Materiella anläggningstillgångar", 11,13),
    FINANCIAL_FIXED_ASSETS("Finansiella anläggningstillgångar", 13,14),
    INVENTORY("Varulager", 14,15),
    RECEIVABLES("Kortfristiga fordringar", 15,18),
    SHORT_TERM_INVESTMENTS("Kortfristiga placeringar", 18,19),
    CASH_AND_BANK("Kassa och bank", 19,20),
    EQUITY_CAPITAL("Eget kapital",20,21),
    UNTAXED_RESERVES("Obeskattade reserver",21,22),
    PROVISIONS("Avsättningar", 22, 23),
    LONG_TERM_LIABILITIES("Långfristiga skulder", 23,24),
    SHORT_TERM_LIABILITIES("Kortfristiga skulder",24,30),

    TOTAL_REVENUE_AND_EXPENSES("Rörelsens intäkter och kostnader", 30,90),
    NET_SALES("Nettoomsättning", 30,38),
    //CHANGES_OF_INVENTORIES("Förändring av lager",49,50),
    ACTIVATED_WORK("Aktiverat arbete för egen räkning", 38, 39),
    OTHER_OPERATING_INCOME("Övriga rörelseintäkter", 39,40),
    MATERIALS("Råvaror och förnödenheter", 40,50),
    OTHER_EXTERNAL_EXPENSES("Övriga externa kostnader", 50,70),
    PERSONNEL("Personalkostnader", 70,77),
    DEPRECIATION("Av- och nedskrivningar", 77, 79),
    OTHER_OPERATING_EXPENSES("Rörelsekostnader", 79,80),
    RESULT_OTHER("Resultat från andelar i andra företag", 80,82 ),
    RESULT_OTHER_FIXED_ASSETS("Resultat från övriga anläggningstillgångar",82,83),
    FINANCIAL_INCOME("Övriga ränteintäkter och liknande resultatposter",83,84),
    FINANCIAL_EXPENSES("Räntekostnader och liknande resultatposter", 84,85),
    APPROPRIATIONS("Bokslutsdispositioner", 88, 89),
    TAX("Skatt på årets resultat", 89,90);



    public static AccountGroup[] fixedAssets() {
        return new AccountGroup[] {
                AccountGroup.FINANCIAL_FIXED_ASSETS,
                AccountGroup.INTANGIBLE_FIXED_ASSETS,
                AccountGroup.TANGIBLE_FIXED_ASSETS
        };
    }

    public static AccountGroup[] currentAssets() {
        return new AccountGroup[] {
                AccountGroup.INVENTORY,
                AccountGroup.RECEIVABLES,
                AccountGroup.SHORT_TERM_INVESTMENTS,
                AccountGroup.CASH_AND_BANK
        };
    }

    public static AccountGroup[] liabilities() {
        return new AccountGroup[] {
                AccountGroup.LONG_TERM_LIABILITIES,
                AccountGroup.SHORT_TERM_LIABILITIES
        };
    }

    public static AccountGroup[] assets() {
        return Stream.concat(Arrays.stream(fixedAssets()), Arrays.stream(currentAssets())).toArray(AccountGroup[]::new);
    }

    public static AccountGroup[] income() {
        return new AccountGroup[] {
                AccountGroup.NET_SALES, AccountGroup.ACTIVATED_WORK, AccountGroup.OTHER_OPERATING_INCOME
        };
    }

    public static AccountGroup[] operatingExpenses() {
        return new AccountGroup[] {
                AccountGroup.MATERIALS,
                AccountGroup.OTHER_EXTERNAL_EXPENSES,
                AccountGroup.PERSONNEL,
                AccountGroup.DEPRECIATION,
                AccountGroup.OTHER_OPERATING_EXPENSES
        };
    }

    private String groupName;
    private int start;
    private int end;

    AccountGroup(final String groupName, final int start, final int end) {
        this.groupName = groupName;
        this.start = start;
        this.end = end;
    }

    public String groupName() {
        return groupName;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }
}
