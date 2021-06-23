package uk.co.icbs.sgc.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.DonationCase;
import java.util.Optional;

@Repository
public interface DonationCaseRepository extends MongoRepository<DonationCase,String>{
    Page<DonationCase> findAllByCategory(@Param("category") String category, Pageable pageable);
    Page<DonationCase> findAllByRecipientName(@Param("recipientName") String recipientName, Pageable pageable);
    Optional<DonationCase> findById(@Param("id") String id);
}