package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.icbs.common.service.model.Status;


@Data
@Document(collection = "donators")
public class Donator extends User {
    @Id
    private String id;
    private Status status;
}