package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCase;


public interface DonationCaseService extends AbstractService<DonationCase> {
    ResponseModel<DonationCase> findAll(Pageable pageable);
    ResponseModel<DonationCase> findAllByCategory(String category, int page, int size);
    ResponseModel<DonationCase> findAllByRecipientName(String recipientName, int page, int size);
}
