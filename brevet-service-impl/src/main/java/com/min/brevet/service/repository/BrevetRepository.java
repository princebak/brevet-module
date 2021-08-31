package com.min.brevet.service.repository;

import com.min.brevet.service.model.Brevet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrevetRepository extends MongoRepository<Brevet,String> {
}
