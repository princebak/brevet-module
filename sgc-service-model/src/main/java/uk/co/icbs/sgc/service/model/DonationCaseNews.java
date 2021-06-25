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
    private String donationCaseTitle;
    private String recipientName;
    private String donatorName;

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
}