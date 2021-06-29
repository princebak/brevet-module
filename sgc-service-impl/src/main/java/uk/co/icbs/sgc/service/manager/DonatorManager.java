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
        if(donator != null){
            donator.getMetadata().setCreated(new Date());
            return donatorRepository.save(donator);
        }

        return null;
    }

    @Override
    public Donator update(Donator donator) {
        logger.info("call to save");
        if(donator != null){
            Donator oldDonator = donatorRepository.findById(donator.getId()).get();
            oldDonator.getMetadata().setUpdated(new Date());
            donator.getMetadata().setUpdated(oldDonator.getMetadata().getUpdated());

            Donator updatedDonator = donatorRepository.save(donator);
            return updatedDonator;
        }

        return null;
    }

    @Override
    public List<Donator> findAll() {
        return donatorRepository.findAll();
    }

    @Override
    public Donator findById(String id) {
        Donator donator = donatorRepository.findById(id).isPresent() ? donatorRepository.findById(id).get() : null;

        if(donator != null){
            int view = donator.getMetadata().getView() + 1 ;
            donator.getMetadata().setView(view);
            donator = update(donator);
        }
        return donator;
    }

    @Override
    public Donator findByEmail(String email) {
        Donator donator = donatorRepository.findByEmail(email).isPresent() ? donatorRepository.findByEmail(email).get() : null;

        if(donator != null){
            int view = donator.getMetadata().getView() + 1 ;
            donator.getMetadata().setView(view);
            donator = update(donator);
        }
        return donator;
    }

    @Override
    public ResponseModel<Donator> findAll(Pageable pageable) {
        Page<Donator> donators = donatorRepository.findAll(pageable);
        return new ResponseModel<>(donators.getTotalPages(), donators.getTotalElements(), donators.getContent());
    }

}