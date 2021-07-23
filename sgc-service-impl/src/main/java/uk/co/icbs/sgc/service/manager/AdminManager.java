package uk.co.icbs.sgc.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.icbs.sgc.service.api.AdminService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Admin;
import uk.co.icbs.sgc.service.repository.AdminRepository;

import java.util.Date;
import java.util.List;

@Service
public class AdminManager implements AdminService {

    private final static Logger logger = LoggerFactory.getLogger(AdminManager.class);
    private final AdminRepository adminRepository;

    public AdminManager(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(Admin admin) {
        logger.info("call to save");
        if(admin != null){
            try {
                admin.getMetadata().setCreated(new Date());
                return adminRepository.save(admin);
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public Admin update(Admin admin) {
        logger.info("call to update");
        if(admin != null){
            try {
                Admin oldAdmin = adminRepository.findById(admin.getId()).get();
                if(oldAdmin != null){
                    admin.getMetadata().setCreated(oldAdmin.getMetadata().getCreated());
                    admin.getMetadata().setUpdated(new Date());

                    return  adminRepository.save(admin);
                }
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<Admin> findAll() {
        try {
            return adminRepository.findAll();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Admin findById(String id) {
        try {
            Admin admin = adminRepository.findById(id).isPresent() ? adminRepository.findById(id).get() : null;

            if(admin != null){
                int view = admin.getMetadata().getView() + 1 ;
                admin.getMetadata().setView(view);
                admin = update(admin);
            }
            return admin;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Admin findByEmail(String email) {
        try {
            Admin admin = adminRepository.findByEmail(email).isPresent() ? adminRepository.findByEmail(email).get() : null;

            if(admin != null){
                int view = admin.getMetadata().getView() + 1 ;
                admin.getMetadata().setView(view);
                admin = update(admin);
            }
            return admin;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseModel<Admin> findAll(Pageable pageable) {
        try {
            Page<Admin> admins = adminRepository.findAll(pageable);
            return new ResponseModel<>(admins.getTotalPages(), admins.getTotalElements(), admins.getContent());
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

}