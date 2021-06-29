package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.DonationCaseService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.repository.DonationCaseRepository;
import uk.co.icbs.sgc.service.repository.DonationRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationCaseManager implements DonationCaseService {

    private final static Logger logger = LoggerFactory.getLogger(DonationCaseManager.class);
    private final DonationCaseRepository donationCaseRepository;
    private final DonationRepository donationRepository;

    @Autowired
    public DonationCaseManager(DonationCaseRepository donationCaseRepository, DonationRepository donationRepository) {
        this.donationCaseRepository = donationCaseRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public DonationCase save(DonationCase donationCase) {
        logger.info("call to save");
        if(donationCase != null){
            donationCase.setStatus((donationCase.getStatus() == null ? Status.CREATED: donationCase.getStatus()));
            donationCase.getMetadata().setCreated(new Date());
            return donationCaseRepository.save(donationCase);
        }

        return null;
    }

    @Override
    public ResponseModel<DonationCase> findAll(Pageable pageable) {
        Page<DonationCase> donationCases = donationCaseRepository.findAll(pageable);
        return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
    }

    @Override
    public ResponseModel<DonationCase> findAllByCategory(String category, int page, int size) {
        if(category != "" && category != null){
            Page<DonationCase> donationCases = donationCaseRepository.findAllByCategory(category, PageRequest.of(page, size));
            return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
        }
        return null;
    }

    @Override
    public ResponseModel<DonationCase> findAllByRecipientId(String recipientId, int page, int size) {
        if(recipientId != "" && recipientId != null){
            Page<DonationCase> donationCases = donationCaseRepository.findAllByRecipientId(recipientId, PageRequest.of(page, size));
            return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
        }
        return null;
    }

    @Override
    public DonationCase findById(String id) {
        DonationCase donationCase = donationCaseRepository.findById(id).isPresent() ? donationCaseRepository.findById(id).get() : null;

        if(donationCase != null){
            int view = donationCase.getMetadata().getView() + 1 ;
            donationCase.getMetadata().setView(view);
            donationCase = update(donationCase);
        }
        return donationCase;
    }
    @Override
    public List<DonationCase> findAllByDonatorId(String donatorId,Pageable pageable) {
        List<Donation> donations = donationRepository.findAllByDonatorId(donatorId);

        List<DonationCase> donationCases = donations.stream()
                                            .map(donation -> donation.getDonationCaseId())
                                            .distinct()
                                            .map(id -> donationCaseRepository.findById(id).get())
                                            .collect(Collectors.toList());

        return donationCases;
    }

    @Override
    public DonationCase update(DonationCase donationCase) {
        if(donationCase != null){
            DonationCase oldDonationCase = donationCaseRepository.findById(donationCase.getId()).get();
            if(oldDonationCase != null){
                donationCase.getMetadata().setCreated(oldDonationCase.getMetadata().getCreated());
                donationCase.getMetadata().setUpdated(new Date());

                return donationCaseRepository.save(donationCase);
            }
        }

        return null;
    }

    @Override
    public List<DonationCase> findAll() {
        return donationCaseRepository.findAll();
    }

}