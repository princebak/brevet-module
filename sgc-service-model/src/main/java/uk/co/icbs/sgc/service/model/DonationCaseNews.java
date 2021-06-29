package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.AbstractEntity;

import java.time.LocalDate;

@Data
@Document(collection = "donation_case_news")
public class DonationCaseNews extends AbstractEntity {
    @Id
    private String id;
    private String message;
    private String donationCaseId;
    private String donationCaseTitle;
    private String recipientName;
    private String donatorName;
    private String recipientId;
    private String donatorId;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDonationcaseTitle() {
        return donationCaseTitle;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public String getDonationCaseId() {
        return donationCaseId;
    }

    public String getDonationCaseTitle() {
        return donationCaseTitle;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getDonatorId() {
        return donatorId;
    }
}