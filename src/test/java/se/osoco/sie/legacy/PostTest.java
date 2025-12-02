package se.osoco.sie.legacy;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PostTest {

    @Test
    public void keyValue() {
        String row = "#FLAGGA  0";
        Post post = new Post(row);
        assertEquals(Key.FLAGGA, post.key());
        assertEquals("0", post.rowTokens().get(0));
    }

    @Test
    public void singleToken() {
        String row = "#FLAGGA  0";
        Post post = new Post(row);
        assertEquals("0", post.rowTokens().get(0));
    }

    @Test
    public void multipleTokens() {
        String row = "#GEN     20240613 \"bs\"";
        Post post = new Post(row);
        List<String> tokens = post.rowTokens();
        assertEquals(Key.GEN, post.key());
        assertEquals("20240613", tokens.get(0));
        assertEquals("bs", tokens.get(1));
    }

    @Test
    public void singleValueQuote() {
        String row = "#FNR     \"bsab\"";
        Post post = new Post(row);
        assertEquals(Key.FNR, post.key());
        assertEquals("bsab", post.rowTokens().get(0));
    }

    @Test
    public void doubleValueQuote() {
        String row = "#PROGRAM \"Edison Ekonomi Start\" \"6.5\"";
        Post post = new Post(row);
        assertEquals(Key.PROGRAM, post.key());
        List<String> rowTokens = post.rowTokens();
        assertEquals("Edison Ekonomi Start", rowTokens.get(0));
        assertEquals("6.5", rowTokens.get(1));
    }

    @Test
    public void multipleValuesQuotes() {
        String row = "#FNAMN   \"Björn Sandgren Konsult AB\"";
        Post post = new Post(row);
        assertEquals(Key.FNAMN, post.key());
        assertEquals("Björn Sandgren Konsult AB", post.rowTokens().get(0));
    }

    @Test
    public void mixedMultipleValuesAndMultipleQuotes() {
        String row = "#PROGRAM some value \"value in quote\" some other value";
        Post post = new Post(row);
        assertEquals(Key.PROGRAM, post.key());
        List<String> rowTokens = post.rowTokens();
        assertEquals("some", rowTokens.get(0));
        assertEquals("value", rowTokens.get(1));
        assertEquals("value in quote", rowTokens.get(2));
        assertEquals("some", rowTokens.get(3));
        assertEquals("other", rowTokens.get(4));
        assertEquals("value", rowTokens.get(5));
    }
}
