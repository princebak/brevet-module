package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.model.DonationCase;


public interface DonationService extends AbstractService<Donation> {
    ResponseModel<Donation> findAll(Pageable pageable);
    ResponseModel<Donation> findAllByDonatorId(String donatorId, Pageable pageable);
    ResponseModel<Donation> findAllByDonationCaseId(String donationCaseId, Pageable pageable);
    Donation findById(String id);
}
