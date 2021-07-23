package uk.co.icbs.sgc.service.api;

import org.springframework.data.domain.Pageable;
import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.Admin;

public interface AdminService extends AbstractService<Admin> {
    Admin findById(String id);
    Admin findByEmail(String email);
    ResponseModel<Admin> findAll(Pageable pageable);
}
