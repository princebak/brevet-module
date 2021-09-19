package com.min.brevet.service.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/mailchimp")
public class BrevetController {

    private final Logger logger = LoggerFactory.getLogger(BrevetController.class);


    @GetMapping("/ping")
    @ResponseBody
    String ping(){
        return "pong";
    }

    @PostMapping("/add-contact")
    @ResponseBody
    Boolean addContact(@RequestBody Contact contact){
        HttpHeaders httpHeaders = new HttpHeaders();

        String mailChimpUrl = "https://us5.api.mailchimp.com/3.0/lists/91b1cbc7da/members";

        final String apiKey = "9bc9f92345b777bd862907992ef3e9cc-us5";

        httpHeaders.setBasicAuth("anystring",apiKey );

        ContactMailChimpDTO contactMailChimpDTO = contact.mapToMailChimpDTO();

        HttpEntity httpEntity = new HttpEntity(contactMailChimpDTO, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try{

            ResponseEntity<ContactMailChimpDTO> response = restTemplate.postForEntity(mailChimpUrl, httpEntity , ContactMailChimpDTO.class);
            ContactMailChimpDTO contactMailChimpDTOResponse = response.getBody();
            logger.info("contact established successfully");

        }catch(Exception ex){
            logger.error("error occurred : " + ex.getMessage());

        }


        return true;
    }


}
