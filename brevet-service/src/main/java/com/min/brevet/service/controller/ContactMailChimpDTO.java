package com.min.brevet.service.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactMailChimpDTO {
//    String id;
//    String unique_email_id;
//    String contact_id;
//    String web_id;
//    String email_type;
//    Boolean consents_to_one_to_one_messaging;
    private String full_name;

    private String email_address;
    private String status = "subscribed";


    @JsonProperty("merge_fields")
    private MergeFields mergeFields;
}
