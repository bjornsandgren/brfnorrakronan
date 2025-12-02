package se.osoco.sie.legacy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class Rar implements Comparable<Rar> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final int year;
    private final LocalDate start;
    private final LocalDate end;

    public Rar(Post post) {
        List<String> rowTokens = post.rowTokens();
        year = Integer.parseInt(rowTokens.get(0));
        start = LocalDate.parse(rowTokens.get(1), FORMATTER);
        end = LocalDate.parse(rowTokens.get(2), FORMATTER);
    }

    public final boolean isLatest() {
        return year == 0;
    }

    public final LocalDate start() {
        return start;
    }

    public final LocalDate end() {
        return end;
    }

    @Override
    public int compareTo(Rar rar) {
        return rar.year - year;
    }
}