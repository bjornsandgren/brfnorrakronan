package se.osoco.sie.legacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KtypTest {

    @Test
    public void singleSpaceDelimiter() {
        Post post = new Post("#KTYP 1060 T");
        Ktyp ktyp = new Ktyp(post);
        assertEquals("1060", ktyp.number);
        assertEquals("T", ktyp.type);
    }

    @Test
    public void multipleSpaceDelimiter() {
        Post post = new Post("#KTYP  1010   T");
        Ktyp ktyp = new Ktyp(post);
        assertEquals("1010", ktyp.number);
        assertEquals("T", ktyp.type);
    }
}
