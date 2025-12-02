package se.osoco.domain.formats;

import java.time.format.DateTimeFormatter;

public class Formats {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter DATE_TIME_FORMATTER_SHORT = DateTimeFormatter.ofPattern("yyyyMMdd");
}