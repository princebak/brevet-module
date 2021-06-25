package uk.co.icbs.sgc.service.model;

import uk.co.icbs.common.service.model.AbstractEntity;

import java.time.LocalDate;

public class Step extends AbstractEntity {
    private String title;
    private String description;
    private String status;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}