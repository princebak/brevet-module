package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.Donator;


public interface DonatorService extends AbstractService<Donator> {
    Donator findById(String id);
    Donator findByEmail(String email);
    ResponseModel<Donator> findAll(Pageable pageable);
}
