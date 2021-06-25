package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.DonationCaseService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.repository.DonationCaseRepository;

import java.util.Date;
import java.util.List;

@Service
public class DonationCaseManager implements DonationCaseService {

    private final static Logger logger = LoggerFactory.getLogger(DonationCaseManager.class);
    private final DonationCaseRepository donationCaseRepository;

    @Autowired
    public DonationCaseManager(DonationCaseRepository donationCaseRepository) {
        this.donationCaseRepository = donationCaseRepository;
    }

    @Override
    public DonationCase save(DonationCase donationCase) {
        if(donationCase != null){
            donationCase.setStatus((donationCase.getStatus() == null ? Status.CREATED: donationCase.getStatus()));
            donationCase.getMetadata().setCreated(new Date());
        }
        assert donationCase != null;
        return donationCaseRepository.save(donationCase);
    }

    @Override
    public ResponseModel<DonationCase> findAll(Pageable pageable) {
        Page<DonationCase> donationCases = donationCaseRepository.findAll(pageable);
        //logger.info("totalElements : " + books.getTotalElements());
        return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
    }

    @Override
    public ResponseModel<DonationCase> findAllByCategory(String category, int page, int size) {
        return filterDonationcases(category, page, size);
    }

    @Override
    public ResponseModel<DonationCase> findAllByRecipientName(String recipientName, int page, int size) {
        return filterDonationcases(recipientName, page, size);
    }

    private ResponseModel<DonationCase> filterDonationcases(String filteringField, int page, int size) {
        if(filteringField != "" && filteringField != null){
            Page<DonationCase> donationCases = donationCaseRepository.findAllByCategory(filteringField, PageRequest.of(page, size));
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
    public DonationCase update(DonationCase donationCase) {
        if(donationCase != null){
            DonationCase oldDonationCase = donationCaseRepository.findById(donationCase.getId()).get();
            oldDonationCase.getMetadata().setUpdated(new Date());
            DonationCase updatedDonationCase = donationCaseRepository.save(oldDonationCase);
            return updatedDonationCase;
        }

        return null;
    }

    @Override
    public List<DonationCase> findAll() {
        return donationCaseRepository.findAll();
    }

}