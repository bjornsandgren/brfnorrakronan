package se.osoco.sie.legacy;


import java.util.List;

class Sru {
    public final String number;
    public final String value;

    public Sru(Post post) {
        List<String> rowTokens = post.rowTokens();
        number = rowTokens.get(0);
        value = rowTokens.get(1);
    }
}
