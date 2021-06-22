package uk.co.icbs.sgc.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.icbs.sgc.service.model.DonationCaseNews;

import java.util.Optional;

@Repository
public interface DonationCaseNewsRepository extends MongoRepository<DonationCaseNews,String> {
    Page<DonationCaseNews> findAllByDonationCaseTitle(@Param("donationCaseTitle") String donationCaseTitle, Pageable pageable);
    Optional<DonationCaseNews> findById(@Param("id") String id);
}