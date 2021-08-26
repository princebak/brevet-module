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

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/donation-case/{donationCaseId}")
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
    @GetMapping("/best-donations")
    public List<Donation> getBestDonations(){
        LOGGER.info("call to getBestDonations : ");
        return donationService.findAll().stream().sorted((f1, f2) -> Double.compare(f2.getAmount(), f1.getAmount()))
                .limit(7).collect(Collectors.toList());
    }
    @GetMapping("/last-donations")
    public List<Donation> getLastDonations(){
        LOGGER.info("call to getLastDonations : ");
        return donationService.findAll().stream().sorted(Comparator.comparing(f -> f.getMetadata().getCreated(),Comparator.reverseOrder()))
                .limit(7).collect(Collectors.toList());
    }

    @GetMapping("/total-donations-amount")
    public Double getTotalDonationsAmount(){
        LOGGER.info("call to totalDonationsAmount");
        return donationService.findAll().stream().mapToDouble(d -> d.getAmount()).sum();
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
