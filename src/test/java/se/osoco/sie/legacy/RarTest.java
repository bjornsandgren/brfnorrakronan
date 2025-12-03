package se.osoco.sie.legacy;

import se.osoco.domain.formats.Formats;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class RarTest {

    @Test
    public void latest() {
        String row = "#RAR 0 20080101 20081231";
        Post post = new Post(row);
        Rar rar = new Rar(post);
        assertTrue(rar.isLatest());
        assertEquals("2008-01-01", rar.start().format(Formats.DATE_TIME_FORMATTER));
        assertEquals("2008-12-31", rar.end().format(Formats.DATE_TIME_FORMATTER));
    }

    @Test
    public void previous() {
        String row = "#RAR -1 20070101 20071231";
        Post post = new Post(row);
        Rar rar = new Rar(post);
        assertFalse(rar.isLatest());
        assertEquals("2007-01-01", rar.start().format(Formats.DATE_TIME_FORMATTER));
        assertEquals("2007-12-31", rar.end().format(Formats.DATE_TIME_FORMATTER));
    }
}