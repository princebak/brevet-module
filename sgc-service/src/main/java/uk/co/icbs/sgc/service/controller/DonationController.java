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

    @PostMapping("")
    public void donate(@RequestBody Donation donation){
        LOGGER.info("call to donate amount: " + donation.getAmount());
        donationService.save(donation);
        DonationCase donationCase = donationCaseService.findById(donation.getDonationCaseId());
        donationCase.setRaisedAmount(donationCase.getRaisedAmount() + donation.getAmount());
        donationCase.setTotalDonatorNumber(donationCase.getTotalDonatorNumber() + 1);
        donationCaseService.update(donationCase);
    }
}