package se.osoco.sie.legacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KontoTest {

    @Test
    public void test() {
        String row = "#KONTO 4031 \"Inköp vissa varor i Sverige 25%\"";
        Post post = new Post(row);
        Konto konto = new Konto(post);
        assertEquals("4031", konto.number);
        assertEquals("Inköp vissa varor i Sverige 25%", konto.name);
    }
}
