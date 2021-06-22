package uk.co.icbs.sgc.service.model;

import java.time.LocalDate;

public class Step {
    private String title;
    private String description;
    private String status;
    private LocalDate created;
    private LocalDate modified;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getModified() {
        return modified;
    }
}