package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.AbstractEntity;
import uk.co.icbs.common.service.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Document(collection = "donation_case_resolving_process")
public class DonationCaseResolvingProcess extends AbstractEntity {
    @Id
    private String id;
    private String description;
    private String donationCaseTitle;
    private Status status;
    private ArrayList<Step> steps;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDonationCaseTile() {
        return donationCaseTitle;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}