package se.osoco.sie.legacy;


import java.util.List;

class Balance {
    public String year;
    public String acccountNumber;
    public String balance;

    public Balance(Post post) {
        List<String> tokens = post.rowTokens();
        year = tokens.get(0);
        acccountNumber = tokens.get(1);
        balance = tokens.get(2);
    }

    public boolean isCurrentYear() {
        return "0".equalsIgnoreCase(year);
    }

    public boolean isLastYear() {
        return "-1".equalsIgnoreCase(year);
    }
}