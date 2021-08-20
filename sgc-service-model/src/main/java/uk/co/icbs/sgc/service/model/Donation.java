package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.AbstractEntity;


@Data
@Document(collection = "donations")
public class Donation extends AbstractEntity {
    @Id
    private String id;
    private String donatorId;
    private String donatorName;
    private String donationCaseId;
    private String donationCaseTitle;
    private Double amount;
    private String Status;

    public String getId() {
        return id;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDonatorId() {
        return donatorId;
    }

    public String getDonationCaseId() {
        return donationCaseId;
    }

    public String getStatus() {
        return Status;
    }

    public String getDonationCaseTitle() {
        return donationCaseTitle;
    }
}