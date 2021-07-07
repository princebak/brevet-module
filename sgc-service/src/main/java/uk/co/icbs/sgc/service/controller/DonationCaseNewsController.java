package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonationCaseNewsService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;
import uk.co.icbs.sgc.service.model.DonationCaseNews;

import java.util.List;

public class DonationCaseNewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonationCaseNewsController.class);
    private final DonationCaseNewsService donationCaseNewsService;

    public DonationCaseNewsController(DonationCaseNewsService donationCaseNewsService) {
        this.donationCaseNewsService = donationCaseNewsService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/list")
    public List<DonationCaseNews> findAllList(){
        LOGGER.info("call to findAll ");
        return donationCaseNewsService.findAll();
    }
    @GetMapping("")
    public @ResponseBody
    ResponseModel<DonationCaseNews> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return donationCaseNewsService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public DonationCaseNews findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return donationCaseNewsService.findById(id);
    }
    @GetMapping("/donation-case/{id}")
    public ResponseModel<DonationCaseNews> findAllByDonationCaseId(@PathVariable String id,
                                                         @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(name = "size", defaultValue = "10", required = false) int size){

        LOGGER.info("call to findAllByDonationCaseId : " + id);
        return donationCaseNewsService.findAllByDonationCaseId(id, page, size);
    }
    @GetMapping("/donator/{id}")
    public ResponseModel<DonationCaseNews> findAllByDonatorId(@PathVariable String id,
                                                                   @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                   @RequestParam(name = "size", defaultValue = "10", required = false) int size){

        LOGGER.info("call to findAllByDonatorId : " + id);
        return donationCaseNewsService.findAllByDonatorId(id, page, size);
    }
    @GetMapping("/recipient/{id}")
    public ResponseModel<DonationCaseNews> findAllByRecipientId(@PathVariable String id,
                                                              @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size){

        LOGGER.info("call to findAllByRecipientId : " + id);
        return donationCaseNewsService.findAllByRecipientId(id, page, size);
    }

    @PostMapping("")
    public @ResponseBody
    DonationCaseNews save(@RequestBody DonationCaseNews donationCaseNews){
        LOGGER.info("call to save DonationCaseNews: " + donationCaseNews.getDonationCaseTitle());
        return donationCaseNewsService.save(donationCaseNews);
    }

    @PostMapping("/update")
    public DonationCaseNews update(@RequestBody DonationCaseNews donationCaseNews){
        LOGGER.info("call to update DonationCaseNews: " + donationCaseNews.getId());
        return donationCaseNewsService.update(donationCaseNews);
    }
}
