package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Recipient;

import java.util.Optional;

public interface RecipientService extends AbstractService<Recipient> {
    ResponseModel<Recipient> findAll(Pageable pageable);
    Recipient findById(String id);
    Recipient findByInstituteName(String name);
    long count();
}
