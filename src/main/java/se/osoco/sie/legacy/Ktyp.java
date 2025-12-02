package se.osoco.sie.legacy;


import java.util.List;

class Ktyp {
    public final String number;
    public final String type;

    public Ktyp(Post post) {
        List<String> rowTokens = post.rowTokens();
        number = rowTokens.get(0);
        type = rowTokens.get(1);
    }

    public boolean isRes() {
        return "i".equalsIgnoreCase(type) || "k".equalsIgnoreCase(type);
    }
}