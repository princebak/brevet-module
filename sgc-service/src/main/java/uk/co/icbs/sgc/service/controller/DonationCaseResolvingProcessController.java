package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonationCaseResolvingProcessService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCaseNews;
import uk.co.icbs.sgc.service.model.DonationCaseResolvingProcess;

import java.util.List;

public class DonationCaseResolvingProcessController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonationCaseResolvingProcessController.class);
    private final DonationCaseResolvingProcessService donationCaseResolvingProcessService;

    public DonationCaseResolvingProcessController(DonationCaseResolvingProcessService donationCaseResolvingProcessService) {
        this.donationCaseResolvingProcessService = donationCaseResolvingProcessService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/list")
    public List<DonationCaseResolvingProcess> findAllList(){
        LOGGER.info("call to findAll ");
        return donationCaseResolvingProcessService.findAll();
    }
    @GetMapping("")
    public @ResponseBody
    ResponseModel<DonationCaseResolvingProcess> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return donationCaseResolvingProcessService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public DonationCaseResolvingProcess findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return donationCaseResolvingProcessService.findById(id);
    }

    @GetMapping("/donation-case/{id}")
    public ResponseModel<DonationCaseResolvingProcess> findAllByDonationCaseId(@PathVariable String id,
                                                                   @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                   @RequestParam(name = "size", defaultValue = "10", required = false) int size){

        LOGGER.info("call to findAllByDonationCaseId : " + id);
        return donationCaseResolvingProcessService.findAllByDonationCaseId(id, page, size);
    }

    @PostMapping("")
    public @ResponseBody
    DonationCaseResolvingProcess save(@RequestBody DonationCaseResolvingProcess donationCaseResolvingProcess){
        LOGGER.info("call to save DonationCaseResolvingProcess: " + donationCaseResolvingProcess.getId());
        return donationCaseResolvingProcessService.save(donationCaseResolvingProcess);
    }

    @PostMapping("/update")
    public DonationCaseResolvingProcess update(@RequestBody DonationCaseResolvingProcess donationCaseResolvingProcess){
        LOGGER.info("call to update DonationCaseResolvingProcess: " + donationCaseResolvingProcess.getId());
        return donationCaseResolvingProcessService.update(donationCaseResolvingProcess);
    }
}
