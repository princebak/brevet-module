package com.min.brevet.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
public class Document {

    @Id
    String id;

    String ref;
    String type;
    Date generated;

    @DBRef
    ResultsExetat results;


}
