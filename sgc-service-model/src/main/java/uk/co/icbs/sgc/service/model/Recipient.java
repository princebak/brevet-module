package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "recipients")
public class Recipient extends User {
    @Id
    private String id;
    private String recipientType;
    private String instituteName;
    private String instituteDescription;
    private Address address;
    private Contact contact;

    public String getId() {
        return id;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public String getInstituteDescription() {
        return instituteDescription;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }
}