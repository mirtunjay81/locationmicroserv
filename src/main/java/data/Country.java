package data;

public enum Country {

    US("UnitedStatesOfAmerica", "US"),
    SWE("Sweden", "SWE"),
    GMNY("Germany", "GERM");

    private String countryName;
    private String countryCode;

    Country(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }
}


