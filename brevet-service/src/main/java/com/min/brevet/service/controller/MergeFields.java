package com.min.brevet.service.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MergeFields {
    @JsonProperty("FNAME")
    private String fName;
    @JsonProperty("LNAME")
    private String lName;
    @JsonProperty("PHONE")
    private String phone;
//    private String ADDRESS;

}
