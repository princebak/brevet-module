package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.model.DonationCase;

import java.util.List;


public interface DonationService extends AbstractService<Donation> {
    ResponseModel<Donation> findAll(Pageable pageable);
    List<Donation> findAllByDonatorId(String donatorId);
    ResponseModel<Donation> findAllByDonationCaseId(String donationCaseId, Pageable pageable);
    Donation findById(String id);
    long count();
}
