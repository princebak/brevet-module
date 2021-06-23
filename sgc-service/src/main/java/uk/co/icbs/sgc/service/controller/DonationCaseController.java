package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.DonationCaseService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.DonationCase;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/donation-cases")
public class DonationCaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonationCaseController.class);

    private final DonationCaseService donationCaseService;

    @Autowired
    public DonationCaseController(DonationCaseService donationCaseService) {
        this.donationCaseService = donationCaseService;
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

    @GetMapping("/category/{category}")
    public ResponseModel<DonationCase> findAllByCategory(HttpServletRequest request, @PathVariable String category,
                                                    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(name = "size", defaultValue = "4", required = false) int size){
//        String category = request.getHeader("category");
        LOGGER.info("call to findAllByCategory : " + category);
        return donationCaseService.findAllByCategory(category, page, size);
    }

    @GetMapping("/recipient")
    public ResponseModel<DonationCase> findAllByRecipientName(HttpServletRequest request,
                                                         @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(name = "size", defaultValue = "4", required = false) int size){
        String recipient = request.getHeader("recipient");
        LOGGER.info("call to findAllByRecipientName : " + recipient);
        return donationCaseService.findAllByRecipientName(recipient, page, size);
    }

    @PostMapping("")
    public @ResponseBody DonationCase save(@RequestBody DonationCase donationCase){
        LOGGER.info("call to save: " + donationCase);
        return donationCaseService.save(donationCase);
    }

    @PostMapping("/update")
    public void update(@RequestBody DonationCase donationCase){
        LOGGER.info("call to update: " + donationCase.getId());
        donationCaseService.update(donationCase);
    }

}
