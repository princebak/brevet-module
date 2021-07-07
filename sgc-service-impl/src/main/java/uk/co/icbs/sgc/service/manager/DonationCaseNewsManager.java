package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.DonationCaseNewsService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.DonationCaseNews;
import uk.co.icbs.sgc.service.repository.DonationCaseNewsRepository;

import java.util.Date;
import java.util.List;

public class DonationCaseNewsManager implements DonationCaseNewsService {

    private final static Logger logger = LoggerFactory.getLogger(DonationCaseNewsManager.class);
    private final DonationCaseNewsRepository donationCaseNewsRepository;

    public DonationCaseNewsManager(DonationCaseNewsRepository donationCaseNewsRepository) {
        this.donationCaseNewsRepository = donationCaseNewsRepository;
    }

    @Override
    public ResponseModel<DonationCaseNews> findAllByDonationCaseId(String donationCaseId, int page, int size) {
        try {
            Page<DonationCaseNews> donationCaseNews = donationCaseNewsRepository.findAllByDonationCaseId(donationCaseId,PageRequest.of(page, size));
            return new ResponseModel<>(donationCaseNews.getTotalPages(), donationCaseNews.getTotalElements(), donationCaseNews.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<DonationCaseNews> findAllByDonatorId(String donatorId, int page, int size) {
        try {
            Page<DonationCaseNews> donationCaseNews = donationCaseNewsRepository.findAllByDonatorId(donatorId,PageRequest.of(page, size));
            return new ResponseModel<>(donationCaseNews.getTotalPages(), donationCaseNews.getTotalElements(), donationCaseNews.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<DonationCaseNews> findAllByRecipientId(String recipientId, int page, int size) {
        try {
            Page<DonationCaseNews> donationCaseNews = donationCaseNewsRepository.findAllByRecipientId(recipientId,PageRequest.of(page, size));
            return new ResponseModel<>(donationCaseNews.getTotalPages(), donationCaseNews.getTotalElements(), donationCaseNews.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
       return null;
    }

    @Override
    public DonationCaseNews save(DonationCaseNews donationCaseNews) {
        logger.info("call to save");
        if(donationCaseNews != null){
            try {
                donationCaseNews.getMetadata().setCreated(new Date());
                return donationCaseNewsRepository.save(donationCaseNews);
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public DonationCaseNews update(DonationCaseNews donationCaseNews) {
        if(donationCaseNews != null){
            try {
                DonationCaseNews oldDonationCase = donationCaseNewsRepository.findById(donationCaseNews.getId()).get();
                if(oldDonationCase != null){
                    donationCaseNews.getMetadata().setCreated(oldDonationCase.getMetadata().getCreated());
                    donationCaseNews.getMetadata().setUpdated(new Date());

                    return donationCaseNewsRepository.save(donationCaseNews);
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<DonationCaseNews> findAll() {
        try {
            return donationCaseNewsRepository.findAll();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<DonationCaseNews> findAll(Pageable pageable) {
        try {
            Page<DonationCaseNews> donationCases = donationCaseNewsRepository.findAll(pageable);
            return new ResponseModel<>(donationCases.getTotalPages(), donationCases.getTotalElements(), donationCases.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public DonationCaseNews findById(String id) {
        try {
            DonationCaseNews donationCaseNews = donationCaseNewsRepository.findById(id).isPresent() ? donationCaseNewsRepository.findById(id).get() : null;

            if(donationCaseNews != null){
                int view = donationCaseNews.getMetadata().getView() + 1 ;
                donationCaseNews.getMetadata().setView(view);
                donationCaseNews = update(donationCaseNews);
            }
            return donationCaseNews;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }
}