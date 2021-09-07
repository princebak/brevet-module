package com.min.brevet.service.repository;

import com.min.brevet.service.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DocumentRepository extends MongoRepository<Document, String> {
    Optional<Document> findByRef(String ref);
    Optional<Document> findByResults_Id(String resultId);
}
