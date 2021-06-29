package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.sgc.service.api.DonationService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.repository.DonationRepository;

import java.util.Date;
import java.util.List;

@Service
public class DonationManager implements DonationService {
    
    private final static Logger logger = LoggerFactory.getLogger(DonationCaseManager.class);
    private final DonationRepository donationRepository;

    public DonationManager(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public ResponseModel<Donation> findAll(Pageable pageable) {
        Page<Donation> donations = donationRepository.findAll(pageable);
        return new ResponseModel<>(donations.getTotalPages(), donations.getTotalElements(), donations.getContent());
    }

    @Override
    public ResponseModel<Donation> findAllByDonatorId(String donatorId, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseModel<Donation> findAllByDonationCaseId(String donationCaseId, Pageable pageable) {
        return null;
    }

    @Override
    public Donation save(Donation donation) {
        logger.info("call to save");
        if(donation != null){
            donation.getMetadata().setCreated(new Date());
            return donationRepository.save(donation);
        }

        return null;
    }

    @Override
    public Donation update(Donation donation) {
        return null;
    }

    @Override
    public List<Donation> findAll() {
        return null;
    }

    @Override
    public Donation findById(String id) {
        return null;
    }
}