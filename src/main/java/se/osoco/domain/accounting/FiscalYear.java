package se.osoco.domain.accounting;


import se.osoco.domain.formats.Formats;
import se.osoco.sie.legacy.SIE;

import java.time.LocalDate;

public class FiscalYear {
    private final LocalDate start;
    private final LocalDate end;

    public FiscalYear(SIE sie) {
        start = sie.currentFiscalYearStart();
        end = sie.currentFiscalYearEnd();
    }

    public String start() {
        return start.format(Formats.DATE_TIME_FORMATTER);
    }

    public String end() {
        return end.format(Formats.DATE_TIME_FORMATTER);
    }

    public String period() {
        return start.format(Formats.DATE_TIME_FORMATTER_SHORT) + " - " + end.format(Formats.DATE_TIME_FORMATTER_SHORT);
    }
}