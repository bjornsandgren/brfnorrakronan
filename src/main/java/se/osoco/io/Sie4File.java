package se.osoco.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import se.osoco.errors.FileNotValidException;

/**
 * Represent a SIE-4 file, https://sie.se/format/. The source file should be encoded using Cp437, so
 * make sure to read the file using that charset. When opening the source file in Android Stuidio,
 * make sure that IMB437 is selected as encoding in the bottom right corner.
 */
public class Sie4File {
    String content;

    public Sie4File(InputStream inputStream) {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("Cp437")));
            char[] cbuf = new char[1024];
            int read = bufferedReader.read(cbuf, 0, cbuf.length);
            if (read == -1) {
                throw new FileNotValidException("Input not vald");
            }
            while (read != -1) {
                content.append(cbuf, 0, read);
                read = bufferedReader.read(cbuf, 0, cbuf.length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.content = content.toString();
    }

    public String content() {
        return content;
    }
}
