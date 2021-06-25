package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonatorService;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.Donator;

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
        LOGGER.info("call to save: " + donator);
        return donatorService.save(donator);
    }

    @PostMapping("/update")
    public void update(@RequestBody Donator donator){
        LOGGER.info("call to update: " + donator.getId());
        donatorService.update(donator);
    }
}
