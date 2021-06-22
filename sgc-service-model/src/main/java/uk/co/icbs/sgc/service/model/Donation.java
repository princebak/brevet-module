package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.AbstractEntity;

import java.time.LocalDate;

@Data
@Document(collection = "donations")
public class Donation extends AbstractEntity {
    @Id
    private String id;
    private String donatorName;
    private String donationCaseTitle;
    private Double amount;
    private LocalDate created;

    public String getId() {
        return id;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public String getDonationCasetitle() {
        return donationCaseTitle;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getCreated() {
        return created;
    }
}