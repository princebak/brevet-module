package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Donator;

import java.util.List;


public interface DonationCaseService extends AbstractService<DonationCase> {
    ResponseModel<DonationCase> findAll(Pageable pageable);
    ResponseModel<DonationCase> findAllByCategory(String category, int page, int size);
    ResponseModel<DonationCase> findAllByRecipientId(String recipientName, int page, int size);

    List<DonationCase> findAllByDonatorId(String id);
}
