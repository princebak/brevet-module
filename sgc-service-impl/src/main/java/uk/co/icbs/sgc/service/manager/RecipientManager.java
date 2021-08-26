package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.RecipientService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Recipient;
import uk.co.icbs.sgc.service.repository.RecipientRepository;

import java.util.Date;
import java.util.List;

@Service
public class RecipientManager implements RecipientService {

    private final static Logger logger = LoggerFactory.getLogger(RecipientManager.class);
    private final RecipientRepository recipientRepository;

    public RecipientManager(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Override
    public Recipient save(Recipient recipient) {
        logger.info("call to save");
        if(recipient != null){
            try {
                recipient.setStatus((recipient.getStatus() == null ? Status.CREATED: recipient.getStatus()));
                recipient.getMetadata().setCreated(new Date());
                 recipientRepository.save(recipient);
                Example<Recipient> exampleMatchingAll = Example.of(recipient, ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("metadata") );
                Recipient recipient1 = recipientRepository.findAll(exampleMatchingAll).get(0);
                return recipient1;
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public Recipient update(Recipient recipient) {
        if(recipient != null){
            try {
                Recipient oldrecipient = recipientRepository.findById(recipient.getId()).get();
                if(oldrecipient != null){
                    recipient.getMetadata().setCreated(oldrecipient.getMetadata().getCreated());
                    recipient.getMetadata().setUpdated(new Date());

                    return recipientRepository.save(recipient);
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Recipient> findAll() {
        try {
            return recipientRepository.findAll();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<Recipient> findAll(Pageable pageable) {
        try {
            Page<Recipient> recipients = recipientRepository.findAll(pageable);
            return new ResponseModel<>(recipients.getTotalPages(), recipients.getTotalElements(), recipients.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Recipient findById(String id) {
        try {
            Recipient recipient = recipientRepository.findById(id).isPresent() ? recipientRepository.findById(id).get() : null;

            if(recipient != null){
                int view = recipient.getMetadata().getView() + 1 ;
                recipient.getMetadata().setView(view);
                recipient = update(recipient);
            }
            return recipient;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Recipient findByInstituteName(String name) {
        try {
            Recipient recipient = recipientRepository.findByInstituteName(name).isPresent() ? recipientRepository.findByInstituteName(name).get() : null;

            if(recipient != null){
                int view = recipient.getMetadata().getView() + 1 ;
                recipient.getMetadata().setView(view);
                recipient = update(recipient);
            }
            return recipient;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public long count() {
        return recipientRepository.count();
    }
}