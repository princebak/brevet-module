package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.Status;


@Data
@Document(collection = "admins")
public class Admin extends User {
    @Id
    private String id;
    private Status status;
    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}