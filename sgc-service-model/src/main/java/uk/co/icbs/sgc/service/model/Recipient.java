package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.Address;
import uk.co.icbs.common.service.model.Status;


@Data
@Document(collection = "recipients")
public class Recipient extends User {
    @Id
    private String id;
    private String recipientType;
    private String instituteName;
    private String instituteDescription;
    private String bankName;
    private String accountNumber;
    private Address address;
    private Status status;

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

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Status getStatus() {
        return status;
    }
}