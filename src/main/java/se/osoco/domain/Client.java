package se.osoco.domain;


import se.osoco.sie.legacy.SIE;

public class Client {
    private OrganisationNumber organisationNumber;
    private String name;

    public Client(String fnamn) {
        this(null, fnamn);
    }

    public Client(final OrganisationNumber organisationNumber, final String organisationName) {
        this.organisationNumber = organisationNumber;
        this.name = organisationName;
    }

    public OrganisationNumber organisationNumber() {
        return organisationNumber;
    }

    public String name() {
        return name.replace("\"", "");
    }

    public String exportFile() {
        return (organisationNumber != null ? organisationNumber : name) + ".se";
    }

    public String toLegacySIE() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#FNAMN");
        stringBuilder.append(SIE.DELIMITER);
        stringBuilder.append("\""); //quotation marks are mandatory to handle space in name
        stringBuilder.append(name);
        stringBuilder.append("\"");
        stringBuilder.append(SIE.NEW_LINE);
        stringBuilder.append("#ORGNR");
        stringBuilder.append(SIE.DELIMITER);
        stringBuilder.append(organisationNumber.value());
        stringBuilder.append(SIE.NEW_LINE);
        return stringBuilder.toString();
    }
}
