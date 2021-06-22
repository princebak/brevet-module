package uk.co.icbs.sgc.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.Donator;

import java.util.Optional;

@Repository
public interface DonatorRepository extends MongoRepository<Donator,String>{
    Optional<Donator> findById(@Param("id") String id);
}