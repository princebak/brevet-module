package com.min.brevet.service.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "results")
@EqualsAndHashCode
public class ResultsExetat {

    @Id
    private String id;

    private String nmPr;
    private String cdPr;
    private String nmCtr;
    private String cdCtr;
    private String nmOp;
    private String cdOp;
    private String nmEts;
    private String cdEts;
    private String ordEts;
    private String cdGst;
    private String nID;
    private String cdCdt;
    private String nmsCdt;
    private String prcnt;
    private String section;
    private String ln;
    private String dateN;
    private String sx;
    private String ecoleCode;
    private String litiges;


    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
