package uk.co.icbs.sgc.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.co.icbs.sgc.service.api.RecipientService;
import uk.co.icbs.sgc.service.api.ResponseModel;
import uk.co.icbs.sgc.service.model.Recipient;

import java.util.List;

public class RecipientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipientController.class);
    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/list")
    public List<Recipient> findAllList(){
        LOGGER.info("call to findAll ");
        return recipientService.findAll();
    }
    @GetMapping("")
    public @ResponseBody
    ResponseModel<Recipient> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                   @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        LOGGER.info("call to findAll");
        return recipientService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Recipient findById(@PathVariable("id") String id){
        LOGGER.info("call to findById : " + id);
        return recipientService.findById(id);
    }

    @PostMapping("")
    public @ResponseBody
    Recipient save(@RequestBody Recipient recipient){
        LOGGER.info("call to save recipient: " + recipient.getEmail());
        return recipientService.save(recipient);
    }

    @PostMapping("/update")
    public Recipient update(@RequestBody Recipient recipient){
        LOGGER.info("call to update recipient: " + recipient.getId());
        return recipientService.update(recipient);
    }
}
