package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.sgc.service.api.DonatorService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donator;
import uk.co.icbs.sgc.service.repository.DonatorRepository;

import java.util.Date;
import java.util.List;

@Service
public class DonatorManager implements DonatorService {

    private final static Logger logger = LoggerFactory.getLogger(DonatorManager.class);
    private final DonatorRepository donatorRepository;

    public DonatorManager(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public Donator save(Donator donator) {
        logger.info("call to save");
        if(donator != null){
            try {
                donator.getMetadata().setCreated(new Date());
                return donatorRepository.save(donator);
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public Donator update(Donator donator) {
        logger.info("call to update");
        if(donator != null){
            try {
                Donator oldDonator = donatorRepository.findById(donator.getId()).get();
                if(oldDonator != null){
                    donator.getMetadata().setCreated(oldDonator.getMetadata().getCreated());
                    donator.getMetadata().setUpdated(new Date());

                    return  donatorRepository.save(donator);
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<Donator> findAll() {
        try {
            return donatorRepository.findAll();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Donator findById(String id) {
        try {
            Donator donator = donatorRepository.findById(id).isPresent() ? donatorRepository.findById(id).get() : null;

            if(donator != null){
                int view = donator.getMetadata().getView() + 1 ;
                donator.getMetadata().setView(view);
                donator = update(donator);
            }
            return donator;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Donator findByEmail(String email) {
        try {
            Donator donator = donatorRepository.findByEmail(email).isPresent() ? donatorRepository.findByEmail(email).get() : null;

            if(donator != null){
                int view = donator.getMetadata().getView() + 1 ;
                donator.getMetadata().setView(view);
                donator = update(donator);
            }
            return donator;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<Donator> findAll(Pageable pageable) {
        try {
            Page<Donator> donators = donatorRepository.findAll(pageable);
            return new ResponseModel<>(donators.getTotalPages(), donators.getTotalElements(), donators.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

}