package uk.co.icbs.sgc.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import uk.co.icbs.sgc.service.model.Contact;

@Data
@Document(collection = "donators")
public class Donator extends User {
    @Id
    private String id;
    private Contact contact;

    public String getId() {
        return id;
    }

    public Contact getContact() {
        return contact;
    }
}