package se.osoco.sie;

import org.junit.Test;
import se.osoco.errors.FileNotFoundException;
import se.osoco.errors.FileNotValidException;
import se.osoco.sie.legacy.SIE;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class SIETest {

    @Test(expected = FileNotFoundException.class)
    public void importFileNotFound() {
        SIE.fromClasspathResource("asdf");
    }

    @Test(expected = FileNotValidException.class)
    public void importedFileNotValid() {
        SIE.fromClasspathResource("/sie/empty.se");
    }

    @Test
    public void sieType1() {
        SIE sie =  SIE.fromClasspathResource("/sie/sie1.se");
        assertEquals("0", sie.flag());
        assertEquals("\"Ledgend\" \"0.0.1\"", sie.program());
        assertEquals("PC8", sie.format());
        assertEquals("20240613 \"bs\"", sie.gen());
        assertEquals("\"Bjorn Sandgren Konsult AB\"", sie.fnamn());
        assertEquals("20240101", sie.currentFiscalYearStart().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        assertEquals("20241231", sie.currentFiscalYearEnd().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        assertEquals(2, sie.konto().size());
        assertEquals("Balanserade utgifter", sie.konto().get(0));
        assertEquals("Patent", sie.konto().get(1));
    }

    @Test
    public void sieType2() {
        SIE sie = SIE.fromClasspathResource("/sie/sie2.se");
        assertEquals("2", sie.sietyp());
        assertEquals("20080630", sie.omfattn());
    }

    @Test
    public void sieType3() {
        SIE sie = SIE.fromClasspathResource("/sie/sie3.se");
        assertEquals("3", sie.sietyp());
    }

    @Test
    public void sieType4() {
        SIE sie = SIE.fromClasspathResource("/sie/SIE4 Exempelfil.SE");
        assertEquals("4", sie.sietyp());
    }

}