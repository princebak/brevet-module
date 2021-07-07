package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.DonationCaseResolvingProcessService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.DonationCaseResolvingProcess;
import uk.co.icbs.sgc.service.repository.DonationCaseResolvingProcessRepository;

import java.util.Date;
import java.util.List;

public class DonationCaseResolvingProcessManager implements DonationCaseResolvingProcessService {

    private final static Logger logger = LoggerFactory.getLogger(DonationCaseManager.class);
    private final DonationCaseResolvingProcessRepository donationResolvingProcessRepository;

    public DonationCaseResolvingProcessManager(DonationCaseResolvingProcessRepository donationResolvingProcessRepository) {
        this.donationResolvingProcessRepository = donationResolvingProcessRepository;
    }

    @Override
    public ResponseModel<DonationCaseResolvingProcess> findAll(Pageable pageable) {
        try {
            Page<DonationCaseResolvingProcess> donationCases = donationResolvingProcessRepository.findAll(pageable);
            return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<DonationCaseResolvingProcess> findAllByDonationCaseId(String donationCaseId, int page, int size) {
        try {
            Page<DonationCaseResolvingProcess> donations = donationResolvingProcessRepository.findAllByDonationCaseId(donationCaseId, PageRequest.of(page, size));
            return new ResponseModel<>(donations.getTotalPages(), donations.getTotalElements(), donations.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public DonationCaseResolvingProcess save(DonationCaseResolvingProcess donationCaseResolvingProcess) {
        logger.info("call to save");
        if(donationCaseResolvingProcess != null){
            try {
                donationCaseResolvingProcess.setStatus((donationCaseResolvingProcess.getStatus() == null ? Status.CREATED: donationCaseResolvingProcess.getStatus()));
                donationCaseResolvingProcess.getMetadata().setCreated(new Date());
                return donationResolvingProcessRepository.save(donationCaseResolvingProcess);
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public DonationCaseResolvingProcess update(DonationCaseResolvingProcess donationCaseResolvingProcess) {
        if(donationCaseResolvingProcess != null){
            try {
                DonationCaseResolvingProcess oldDonationCaseResolvingProcess = donationResolvingProcessRepository.findById(donationCaseResolvingProcess.getId()).get();
                if(oldDonationCaseResolvingProcess != null){
                    donationCaseResolvingProcess.getMetadata().setCreated(oldDonationCaseResolvingProcess.getMetadata().getCreated());
                    donationCaseResolvingProcess.getMetadata().setUpdated(new Date());

                    return donationResolvingProcessRepository.save(donationCaseResolvingProcess);
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<DonationCaseResolvingProcess> findAll() {
        try {
            return donationResolvingProcessRepository.findAll();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public DonationCaseResolvingProcess findById(String id) {
        try {
            DonationCaseResolvingProcess donationCaseResolvingProcess = donationResolvingProcessRepository.findById(id).isPresent() ? donationResolvingProcessRepository.findById(id).get() : null;

            if(donationCaseResolvingProcess != null){
                int view = donationCaseResolvingProcess.getMetadata().getView() + 1 ;
                donationCaseResolvingProcess.getMetadata().setView(view);
                donationCaseResolvingProcess = update(donationCaseResolvingProcess);
            }
            return donationCaseResolvingProcess;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }
}