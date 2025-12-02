package se.osoco.sie.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import se.osoco.Application;
import se.osoco.bas.Bas24;
import se.osoco.domain.Client;
import se.osoco.domain.OrganisationNumber;
import se.osoco.domain.Verification;
import se.osoco.domain.account.AccountMetaData;
import se.osoco.domain.accounting.AccountChart;
import se.osoco.errors.FileNotFoundException;
import se.osoco.io.Sie4File;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class SIE {

    public static Character DELIMITER = ' ';
    public static String NEW_LINE = "\r\n";

    private static final String PROGRAM = "#PROGRAM" + DELIMITER + Application.NAME + NEW_LINE;
    private static final Logger LOGGER = LoggerFactory.getLogger(SIE.class);

    public static SIE fromClasspathResource(String fileName) {
        InputStream inputStream = SIE.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(fileName);
        }
        Sie4File sieFile = new Sie4File(inputStream);
        return new SIE(sieFile);
    }

    public static SIE fromMultipartFile(MultipartFile multipartFile) {
        try {
            return new SIE(new Sie4File(multipartFile.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SIE fromScratch(Client client) {
        return new SIE(client);
    }

    private final List<Post> posts = new ArrayList<>();
    private final AccountChart accountChart;

    SIE(List<Post> posts) {
        this.posts.addAll(posts);
        accountChart = new SIELegacyAccountChart(this);
    }

    private SIE(Sie4File sieFile) {
        posts.addAll(parse(sieFile.content()));
        accountChart = new SIELegacyAccountChart(this);
    }

    private SIE(Client client) {
        posts.addAll(parse(client.toLegacySIE()));
        accountChart = Bas24.getInstance().accountChart();
    }

    private List<Post> parse(String content) {
        List<Post> posts = new ArrayList<>();
        //Parse all posts
        String delimiter = "#";
        String[] lines = content.split(delimiter);
        for (String line : lines) {
            if (line != null && line.trim().length() != 0) {
                try {
                    posts.add(new Post(delimiter + line));
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Error parsing post: ", e);
                }
            }
        }
        return posts;
    }

    public String flag() {
        return posts().stream().filter(p -> p.key() == Key.FLAGGA).findFirst().get().value();
    }

    public String program() {
        return posts().stream().filter(p -> p.key() == Key.PROGRAM).findFirst().get().value();
    }

    public String format() {
        return posts().stream().filter(p -> p.key() == Key.FORMAT).findFirst().get().value();
    }

    public String gen() {
        return posts().stream().filter(p -> p.key() == Key.GEN).findFirst().get().value();
    }

    public String fnamn() {
        return posts().stream().filter(p -> p.key() == Key.FNAMN).findFirst().get().value();
    }

    public List<Rar> rars() {
        List<Post> posts = posts().stream().filter(p -> p.key() == Key.RAR).toList();
        List<Rar> rars = posts.stream().map(Rar::new).sorted(Rar::compareTo).toList();
        return rars;
    }

    public List<String> konto() {
        return accountChart.asList().stream().map(AccountMetaData::name).toList();
    }

    public int size() {
        return posts.size();
    }

    public Client client() {
        String orgName = fnamn();
        Orgnr orgnr = null;
        for (Post post : posts()) {
            if (Objects.requireNonNull(post.key()) == Key.ORGNR) {
                orgnr = new Orgnr(post);
            }
        }
        if (orgnr == null || orgnr.orgnr == null) {
            return new Client(fnamn());
        }
        return new Client(new OrganisationNumber(orgnr.orgnr), orgName);
    }

    public AccountChart chartOfAccounts() {
        return accountChart;
    }

    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Post post : posts) {
            if (post.key() == Key.PROGRAM) {
                stringBuilder.append(PROGRAM);
            } else {
                stringBuilder.append(post.raw());
            }
        }
        return stringBuilder.toString();
    }

    public SIE add(Verification latestVerification) {
        if (latestVerification != null) {
            String serialized = latestVerification.serialize();
            posts.addAll(parse(serialized));
        }
        return this;
    }

    public String sietyp() {
        return posts().stream().filter(p -> p.key() == Key.SIETYP).findFirst().get().value();
    }

    public String omfattn() {
        return posts().stream().filter(p -> p.key() == Key.OMFATTN).findFirst().get().value();
    }

    public String psaldo() {
        throw new RuntimeException("Not implemented yet!");
    }

    public String pbudget() {
        throw new RuntimeException("Not implemented yet!");
    }

    public Map<String,List<Verification>> verifications() {
        List<Post> posts = posts();
        Map<String,List<Verification>> series = new HashMap<>();
        int i = 0;
        while (i < posts.size()) {
            Post post = posts.get(i);
            if (post.key() == Key.VER) {
                Ver ver = new Ver(post);
                List<Trans> transList = new ArrayList<>();
                while (++i < posts.size() && posts.get(i).key() == Key.TRANS) {
                    post = posts.get(i);
                    Trans trans = new Trans(post);
                    transList.add(trans);
                }
                Verification verification = ver.asVerification(transList, chartOfAccounts());
                List<Verification> verifications = series.get(verification.serie());
                if (verifications == null) {
                    verifications = new ArrayList<>();
                    series.put(verification.serie(), verifications);
                }
                verifications.add(verification);
            }
            else {
                //ignore all others
                i++;
            }
        }
        return series;
    }

    public List<Post> posts() {
        return Collections.unmodifiableList(posts);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Post post : posts) {
            stringBuilder.append(post.raw());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SIE sie1to4)) {
            return false;
        }
        return serialize().equals(sie1to4.serialize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(posts);
    }

    private boolean isSIE4() {
        Pattern pattern = Pattern.compile("(.*)#SIETYP\\s*4(.*)", Pattern.DOTALL);
        return pattern.matcher(serialize()).matches();
    }

    private boolean isSIE3() {
        Pattern pattern = Pattern.compile("(.*)#SIETYP\\s*3(.*)", Pattern.DOTALL);
        return pattern.matcher(serialize()).matches();
    }

    private boolean isSIE2() {
        Pattern pattern = Pattern.compile("(.*)#SIETYP\\s*2(.*)", Pattern.DOTALL);
        return pattern.matcher(serialize()).matches();
    }

    private boolean isSIE1() {
        if (!serialize().contains("#SIETYP")) {
            return true;
        }
        Pattern pattern = Pattern.compile("(.*)#SIETYP\\s*1(.*)", Pattern.DOTALL);
        return pattern.matcher(serialize()).matches();
    }

    public LocalDate currentFiscalYearStart() {
        for(Rar rar : rars()) {
            if (rar.isLatest()) {
                return rar.start();
            }
        }
        throw new IllegalArgumentException("No current fiscal-year-start found!");
    }

    public LocalDate currentFiscalYearEnd() {
        for(Rar rar : rars()) {
            if (rar.isLatest()) {
                return rar.end();
            }
        }
        throw new IllegalArgumentException("No current fiscal-year-end found!");
    }
}