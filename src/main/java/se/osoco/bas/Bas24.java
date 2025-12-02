package se.osoco.bas;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bas24 {

    private static Bas24 INSTANCE;

    public static Bas24 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = readFile("/bas/Kontoplan-BAS-2024.xlsx");
        }
        return INSTANCE;
    }

    static Bas24 readFile(String filePath) {
        URL resource = Bas24.class.getResource(filePath);
        File file = null;
        try {
            file = new File(resource.toURI());
            FileInputStream input = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(input);
            return new Bas24(workbook);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Workbook workbook;

    public Bas24(Workbook workbook) {
        this.workbook = workbook;
    }

    public AccountChart accountChart() {
        Sheet sheet = workbook.getSheetAt(0);
        List<AccountMetaData> accounts = new ArrayList<>();
        for (int index = 11; index <= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);
            if (row != null) {
                Cell number = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                Cell text = row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (number != null && text != null) {
                    accounts.add(new BasAccount((int) number.getNumericCellValue(), text.getStringCellValue()));
                }
            }
        }
        return new BasAccountChart(accounts);
    }
}
