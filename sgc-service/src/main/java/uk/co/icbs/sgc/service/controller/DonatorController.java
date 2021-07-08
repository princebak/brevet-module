package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonatorService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donators")
public class DonatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonatorController.class);

    private final DonatorService donatorService;

    public DonatorController(DonatorService donatorService) {
        this.donatorService = donatorService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/list")
    public List<Donator> findAllList(){
        LOGGER.info("call to findAll ");
        return donatorService.findAll();
    }

    @GetMapping("")
    public @ResponseBody
    ResponseModel<Donator> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return donatorService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Donator findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return donatorService.findById(id);
    }

    @GetMapping("/email/{email}")
    public Donator findByEmail(@PathVariable("email") String email){
        LOGGER.info("call to findByEmail : " + email);
        Donator donator= donatorService.findByEmail(email);
        return donator;
    }

    @PostMapping("")
    public @ResponseBody
    Donator save(@RequestBody Donator donator){
        LOGGER.info("call to save donator: " + donator.getEmail());
        return donatorService.save(donator);
    }

    @PostMapping("/update")
    public Donator update(@RequestBody Donator donator){
        LOGGER.info("call to update donator: " + donator.getId());
        return donatorService.update(donator);
    }
}
