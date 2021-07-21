package uk.co.icbs.sgc.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.Recipient;

import java.util.Optional;
@Repository
public interface RecipientRepository extends MongoRepository<Recipient,String>{
    Optional<Recipient> findById(@Param("id") String id);
    Optional<Recipient> findByInstituteName(@Param("name") String name);
}