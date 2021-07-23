package uk.co.icbs.sgc.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String>{
    Optional<Admin> findById(@Param("id") String id);
    Optional<Admin> findByEmail(@Param("email") String email);
}