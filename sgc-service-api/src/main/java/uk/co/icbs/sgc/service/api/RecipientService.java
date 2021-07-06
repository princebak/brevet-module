package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Recipient;

public interface RecipientService extends AbstractService<Recipient> {
    ResponseModel<Recipient> findAll(Pageable pageable);
    Recipient findById(String id);
}
