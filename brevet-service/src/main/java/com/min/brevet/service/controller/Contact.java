package com.min.brevet.service.controller;

import lombok.Data;

@Data
public class Contact{
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
//    private String address;
    ContactMailChimpDTO mapToMailChimpDTO(){

        ContactMailChimpDTO contactMailChimpDTO = new ContactMailChimpDTO();

        contactMailChimpDTO.setEmail_address(email);
        contactMailChimpDTO.setFull_name(firstName + " " + lastName);
        MergeFields merge_fields = new MergeFields();
        merge_fields.setLName (lastName);
        merge_fields.setFName(firstName);
        merge_fields.setPhone(phone);
//        merge_fields.setADDRESS(address);

        contactMailChimpDTO.setMergeFields(merge_fields);

        return contactMailChimpDTO;
    }
}
