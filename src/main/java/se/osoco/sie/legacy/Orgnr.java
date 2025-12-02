package se.osoco.sie.legacy;

import java.util.List;

class Orgnr {
    public final String orgnr;

    public Orgnr(Post post) {
        List<String> rowTokens = post.rowTokens();
        orgnr = rowTokens.get(0);
    }
}
