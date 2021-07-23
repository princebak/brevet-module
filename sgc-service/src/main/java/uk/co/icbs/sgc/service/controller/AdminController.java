package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.AdminService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Admin;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/list")
    public List<Admin> findAllList(){
        LOGGER.info("call to findAll ");
        return adminService.findAll();
    }

    @GetMapping("")
    public @ResponseBody
    ResponseModel<Admin> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return adminService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Admin findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return adminService.findById(id);
    }

    @GetMapping("/email/{email}")
    public Admin findByEmail(@PathVariable("email") String email){
        LOGGER.info("call to findByEmail : " + email);
        Admin Admin= adminService.findByEmail(email);
        return Admin;
    }

    @PostMapping("")
    public @ResponseBody
    Admin save(@RequestBody Admin admin){
        LOGGER.info("call to save admin: " + admin.getEmail());
        return adminService.save(admin);
    }

    @PostMapping("/update")
    public Admin update(@RequestBody Admin admin){
        LOGGER.info("call to update admin: " + admin.getId());
        return adminService.update(admin);
    }
}
