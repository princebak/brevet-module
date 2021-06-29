package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.AbstractEntity;
import uk.co.icbs.common.service.model.Status;

import java.time.LocalDate;

@Data
@Document(collection = "donation_cases")
public class DonationCase extends AbstractEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private Status status;
    private String recipientName;
    private Double aimedAmount;
    private Double raisedAmount;
    private Long totalDonatorNumber;
    private Boolean isUrgent;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public Double getAimedAmount() {
        return aimedAmount;
    }

    public Double getRaisedAmount() {
        return raisedAmount;
    }

    public Long getTotalDonatorNumber() {
        return totalDonatorNumber;
    }

    public Boolean getUrgent() {
        return isUrgent;
    }

    public void setRaisedAmount(Double raisedAmount) {
        this.raisedAmount = raisedAmount;
    }

    public void setTotalDonatorNumber(Long totalDonatorNumber) {
        this.totalDonatorNumber = totalDonatorNumber;
    }
}