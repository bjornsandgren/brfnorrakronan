package se.osoco.sie.legacy;

import java.util.List;

class Program extends Post {
    public final String programnamn;
    public final String version;

    public Program(String line) {
        super((line));
        List<String> rowTokens = rowTokens();
        programnamn = rowTokens.get(0);
        version = rowTokens.get(1);
    }
}
