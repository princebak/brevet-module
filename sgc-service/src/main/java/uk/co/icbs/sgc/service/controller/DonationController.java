package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonationCaseService;
import uk.co.icbs.sgc.service.api.DonationService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Donation;
import uk.co.icbs.sgc.service.model.DonationCase;

@RestController
@RequestMapping("/api/v1/donations")
public class DonationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonationController.class);
    private final DonationService donationService;
    private final DonationCaseService donationCaseService;

    public DonationController(DonationService donationService, DonationCaseService donationCaseService) {
        this.donationService = donationService;
        this.donationCaseService = donationCaseService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("")
    public @ResponseBody
    ResponseModel<Donation> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return donationService.findAll(PageRequest.of(page, size));
    }
    @GetMapping("/donationCase/{donationCaseId}")
    public @ResponseBody
    ResponseModel<Donation> findAllByDonationCaseId(@PathVariable String donationCaseId,
                                                    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAllByDonationCaseId "+donationCaseId);
        return donationService.findAllByDonationCaseId(donationCaseId,PageRequest.of(page, size));
    }
    @GetMapping("/count")
    public long count(){
        LOGGER.info("call to count : ");
        return donationService.count();
    }

    @PostMapping("")
    public Donation donate(@RequestBody Donation donation){
        LOGGER.info("call to donate amount: " + donation.getAmount());
        if(donation != null){
            Donation newDonation = donationService.save(donation);
            DonationCase donationCase = donationCaseService.findById(donation.getDonationCaseId());
            donationCase.setRaisedAmount(donationCase.getRaisedAmount() + donation.getAmount());
            donationCase.setTotalDonatorNumber(donationCase.getTotalDonatorNumber() + 1);
            if(donationCase.getRaisedAmount() >= donationCase.getAimedAmount()){
                donationCase.setStatus("Closed");
            }else{
                donationCase.setStatus("Processing");
            }
            donationCaseService.update(donationCase);
            return newDonation;
        }
        return donation;
    }
    @PostMapping("/update")
    public Donation updateDonation(@RequestBody Donation donation){
        LOGGER.info("call to updateDonation amount: " + donation.getAmount());
        if(donation != null){
            Donation newDonation = donationService.update(donation);
            DonationCase donationCase = donationCaseService.findById(donation.getDonationCaseId());
            donationCase.setRaisedAmount(donationCase.getRaisedAmount() + donation.getAmount());
            donationCaseService.update(donationCase);
            return newDonation;
        }
        return donation;
    }

}
