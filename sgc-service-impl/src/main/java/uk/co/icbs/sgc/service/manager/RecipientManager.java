package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.Status;
import uk.co.icbs.sgc.service.api.RecipientService;
import uk.co.icbs.sgc.service.api.ResponseModel;
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
            recipient.setStatus((recipient.getStatus() == null ? Status.CREATED: recipient.getStatus()));
            recipient.getMetadata().setCreated(new Date());
            return recipientRepository.save(recipient);
        }

        return null;
    }

    @Override
    public Recipient update(Recipient recipient) {
        if(recipient != null){
            Recipient oldrecipient = recipientRepository.findById(recipient.getId()).get();
            if(oldrecipient != null){
                recipient.getMetadata().setCreated(oldrecipient.getMetadata().getCreated());
                recipient.getMetadata().setUpdated(new Date());

                return recipientRepository.save(recipient);
            }
        }

        return null;
    }

    @Override
    public List<Recipient> findAll() {
        return recipientRepository.findAll();
    }

    @Override
    public ResponseModel<Recipient> findAll(Pageable pageable) {
        Page<Recipient> recipients = recipientRepository.findAll(pageable);
        return new ResponseModel<>(recipients.getTotalPages(), recipients.getTotalElements(), recipients.getContent());
    }

    @Override
    public Recipient findById(String id) {
        Recipient recipient = recipientRepository.findById(id).isPresent() ? recipientRepository.findById(id).get() : null;

        if(recipient != null){
            int view = recipient.getMetadata().getView() + 1 ;
            recipient.getMetadata().setView(view);
            recipient = update(recipient);
        }
        return recipient;
    }
}