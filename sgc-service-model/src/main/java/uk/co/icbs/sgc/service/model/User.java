package uk.co.icbs.sgc.service.model;

import uk.co.icbs.common.service.model.AbstractEntity;

public class User extends AbstractEntity{
    private String first;
    private String  last;
    private String  email;
    private String  password;
    private String  phone;
    private String  type;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }
}