package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.sgc.service.api.DonationService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Donator;
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
    public List<Donation> findAllByDonatorId(String donatorId) {
        return donationRepository.findAllByDonatorId(donatorId);
    }

    @Override
    public ResponseModel<Donation> findAllByDonationCaseId(String donationCaseId, Pageable pageable) {
        Page<Donation> donations = donationRepository.findAllByDonationCaseId(donationCaseId,pageable);
        return new ResponseModel<>(donations.getTotalPages(), donations.getTotalElements(), donations.getContent());
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
        if(donation != null){
            Donation oldDonation = donationRepository.findById(donation.getId()).get();
            if(oldDonation != null){
                donation.getMetadata().setCreated(oldDonation.getMetadata().getCreated());
                donation.getMetadata().setUpdated(new Date());

                return donationRepository.save(donation);
            }
        }

        return null;
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public Donation findById(String id) {
        Donation donation = donationRepository.findById(id).isPresent() ? donationRepository.findById(id).get() : null;

        if(donation != null){
            int view = donation.getMetadata().getView() + 1 ;
            donation.getMetadata().setView(view);
            donation = update(donation);
        }
        return donation;
    }
}