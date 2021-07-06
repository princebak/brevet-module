package uk.co.icbs.sgc.service.model;

import uk.co.icbs.common.service.model.AbstractEntity;
import uk.co.icbs.common.service.model.Status;

import java.time.LocalDate;

public class Step extends AbstractEntity {
    private String title;
    private String description;
    private Status status;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
}