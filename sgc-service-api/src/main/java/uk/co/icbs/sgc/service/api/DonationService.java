package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.sgc.service.model.Donation;


public interface DonationService {
    ResponseModel<Donation> findAllByDonatorName(String donatorName, Pageable pageable);
    ResponseModel<Donation> findAllByDonationCasetitle(String donationCasetitle, Pageable pageable);
    Donation findById(String id);
}
