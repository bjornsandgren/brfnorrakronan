package se.osoco.sie.legacy;

import java.util.List;

class Konto {

    public final String number;
    public final String name;
    public Ktyp ktyp;
    public Sru sru;
    public Balance ib;
    public Balance ub;
    public Balance res;
    public Balance resLastYear;

    private final Post post;

    public Konto(Post post) {
        this.post = post;
        List<String> rowTokens = post.rowTokens();
        number = rowTokens.get(0);
        name = rowTokens.get(1).trim().replace("\"", "");
    }

    public boolean isRes() {
        return ktyp != null && ktyp.isRes();
    }

    @Override
    public String toString() {
        return post.toString();
    }
}