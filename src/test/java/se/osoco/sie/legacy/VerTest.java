package se.osoco.sie.legacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerTest {

    @Test
    public void singleSpaceDelimiter() {
        Post post = new Post("#VER A 53 20211126 Reklam 20211113");
        Ver ver = new Ver(post);
        assertEquals("A", ver.serie);
        assertEquals("53", ver.vernr);
        assertEquals("20211126", ver.verdatum);
        assertEquals("Reklam", ver.vertext);
    }

    @Test
    public void multipleSpaceDelimiter() {
        Post post = new Post("#VER 0     1     20240101 \"Kontoavslut 2099 mot 2098\" 20240101");
        Ver ver = new Ver(post);
        assertEquals("0", ver.serie);
        assertEquals("1", ver.vernr);
        assertEquals("20240101", ver.verdatum);
        assertEquals("Kontoavslut 2099 mot 2098", ver.vertext);
    }

    @Test
    public void serieContainingSpace() {
        Post post = new Post("#VER  \"B Leverantörsfakturor\"    1     20240101 \"Kontoavslut 2099 mot 2098\" 20240101");
        Ver ver = new Ver(post);
        assertEquals("B Leverantörsfakturor", ver.serie);
        assertEquals("1", ver.vernr);
        assertEquals("20240101", ver.verdatum);
        assertEquals("Kontoavslut 2099 mot 2098", ver.vertext);
    }

    /**
    @Test
    public void textContainingSpecialChar() {
        String row = "0     1     20240101 \"Kontoavslut 2099 \"mot\" 2098\" 20240101";
        VER ver = new VER(row);
        assertEquals("0", ver.serie);
        assertEquals(1, ver.vernr);
        assertEquals(LocalDate.of(2024, 01, 01), ver.verdatum);
        assertEquals("Kontoavslut 2099 mot 2098", ver.vertext);
    }
    */
}
