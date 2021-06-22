package uk.co.icbs.sgc.service.model;

import uk.co.icbs.common.service.model.AbstractEntity;

public class Contact extends AbstractEntity {

    private String countryCode;
    private String phone;
    private String email;
    private String website;
    private String linkedin;
    private String facebook;
    private String twitter;

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }
}
