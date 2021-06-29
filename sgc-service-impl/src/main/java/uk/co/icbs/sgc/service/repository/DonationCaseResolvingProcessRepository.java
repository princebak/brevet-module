package uk.co.icbs.sgc.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.DonationCaseNews;
import uk.co.icbs.sgc.service.model.DonationCaseResolvingProcess;

import java.util.Optional;

@Repository
public interface DonationCaseResolvingProcessRepository extends MongoRepository<DonationCaseResolvingProcess,String>{
    Page<DonationCaseResolvingProcess> findAllByDonationCaseId(@Param("donationCaseId") String donationCaseId, Pageable pageable);
    Optional<DonationCaseResolvingProcess> findById(@Param("id") String id);
}