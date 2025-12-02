package se.osoco.sie.legacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransTest {

    @Test
    public void singleSpaceDelimiter() {
        Post post = new Post("#TRANS 1060 {} -195.00");
        Trans trans = new Trans(post);
        assertEquals("1060", trans.kontonr);
        assertEquals("-195.00", trans.belopp);
    }

    @Test
    public void multipleSpaceDelimiter() {
        Post post = new Post("#TRANS 2099 {}    563650.15 20240101");
        Trans trans = new Trans(post);
        assertEquals("2099", trans.kontonr);
        assertEquals("563650.15", trans.belopp);
    }

    @Test
    public void test() {
        Post post = new Post("#TRANS 7690 {} 174.12");
        Trans trans = new Trans(post);
        assertEquals("7690", trans.kontonr);
        assertEquals("174.12", trans.belopp);
    }

    @Test
    public void objekt() {
        Post post = new Post("#TRANS 3041 {1 Nord} -3550.00");
        Trans trans = new Trans(post);
        assertEquals("3041", trans.kontonr);
        assertEquals("-3550.00", trans.belopp);
    }

    @Test
    public void objekt2() {
        Post post = new Post("#TRANS 3590 {1 Nord 6 0001} -5500.00");
        Trans trans = new Trans(post);
        assertEquals("3590", trans.kontonr);
        assertEquals("-5500.00", trans.belopp);
    }
}