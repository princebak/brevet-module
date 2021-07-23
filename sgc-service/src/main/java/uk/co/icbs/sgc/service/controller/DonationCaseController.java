package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonationCaseService;
import uk.co.icbs.sgc.service.api.DonatorService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donation-cases")
public class DonationCaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonationCaseController.class);

    private final DonationCaseService donationCaseService;
    private final DonatorService donatorService;

    @Autowired
    public DonationCaseController(DonationCaseService donationCaseService, DonatorService donatorService) {
        this.donationCaseService = donationCaseService;
        this.donatorService = donatorService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("")
    public @ResponseBody ResponseModel<DonationCase> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return donationCaseService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public DonationCase findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return donationCaseService.findById(id);
    }
    @GetMapping("/count")
    public long count(){
        LOGGER.info("call to count : ");
        return donationCaseService.count();
    }

    @GetMapping("/category/{category}")
    public ResponseModel<DonationCase> findAllByCategory(@PathVariable String category,
                                                    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(name = "size", defaultValue = "4", required = false) int size){

        LOGGER.info("call to findAllByCategory : " + category);
        return donationCaseService.findAllByCategory(category, page, size);
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseModel<DonationCase> findAllByRecipientId(@PathVariable String recipientId,
                                                         @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(name = "size", defaultValue = "4", required = false) int size){

        LOGGER.info("call to findAllByRecipientId : " + recipientId);
        return donationCaseService.findAllByRecipientId(recipientId, page, size);
    }

    @GetMapping("/donator/{id}")
    public List<DonationCase> findAllByDonatorId(@PathVariable String id){

        LOGGER.info("call to findAllByDonatorId : " + id);

        return donationCaseService.findAllByDonatorId(id);
    }

    @PostMapping("")
    public @ResponseBody DonationCase save(@RequestBody DonationCase donationCase){
        LOGGER.info("call to save: " + donationCase);
        return donationCaseService.save(donationCase);
    }

    @PostMapping("/update")
    public DonationCase update(@RequestBody DonationCase donationCase){
        LOGGER.info("call to update: " + donationCase.getId());
        return donationCaseService.update(donationCase);
    }

}
