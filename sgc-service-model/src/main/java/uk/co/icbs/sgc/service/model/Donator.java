package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "donators")
public class Donator extends User {
    @Id
    private String id;

    public String getId() {
        return id;
    }
}