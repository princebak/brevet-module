package uk.co.icbs.sgc.service.model;

public class Address {
    private  String streetNumber;
    private  String firstLineAddress;
    private  String secondLineAddress;
    private  String town;
    private  String postcode;
    private  String country;

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    public String getSecondLineAddress() {
        return secondLineAddress;
    }

    public String getTown() {
        return town;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }
}