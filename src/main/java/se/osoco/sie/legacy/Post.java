package se.osoco.sie.legacy;


import java.util.*;

class Post {
    private final String raw;
    private final Key rowKey;
    private final String rowValue;

    public Post(String line) {
        raw = line;
        String[] row = line.split(" ", 2);
        String keyStr = row[0].replace("#", "");
        rowKey = Key.valueOf(keyStr);
        rowValue = row[1].trim();
    }

    public String raw() {
        return raw;
    }

    public Key key() {
        return rowKey;
    }

    public String value() {
        return rowValue;
    }

    @Override
    public String toString() {
        return raw;
    }

    public List<String> rowTokens() {
        List<String> tokens = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<rowValue.length(); i++) {
            if (rowValue.charAt(i) == '{' || rowValue.charAt(i) == '}') {
                continue;
            }
            if (rowValue.charAt(i) == '\"') {
                i++;
                while (rowValue.charAt(i) != '\"') {
                    stringBuilder.append(rowValue.charAt(i++));
                }
            }
            if (Character.isSpaceChar(rowValue.charAt(i))) {
                if (!stringBuilder.isEmpty()) {
                    tokens.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            } else {
                stringBuilder.append(rowValue.charAt(i));
            }
        }
        tokens.add(stringBuilder.toString());

        List<String> cleanedTokens = new ArrayList<>();
        for (String token : tokens) {
            cleanedTokens.add(token.replace("\"", "").trim());
        }
        return cleanedTokens;
    }

    public String singleValue() {
        return rowTokens().get(0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return Objects.equals(raw, post.raw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raw);
    }
}
