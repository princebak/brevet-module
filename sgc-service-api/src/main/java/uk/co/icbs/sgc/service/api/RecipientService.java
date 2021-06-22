package uk.co.icbs.sgc.service.api;

import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.Recipient;

public interface RecipientService extends AbstractService<Recipient> {
    Recipient findById(String id);
}
