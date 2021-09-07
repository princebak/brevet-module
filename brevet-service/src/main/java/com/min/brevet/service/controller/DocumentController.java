package com.min.brevet.service.controller;

import com.min.brevet.service.api.DocumentService;
import com.min.brevet.service.api.ResponseModel;
import com.min.brevet.service.common.util.Constant;
import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.Document;
import com.min.brevet.service.model.ResultsExetat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    private DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping("")
    @ResponseBody
    ResponseModel<List<Document>> findAll(@RequestParam(name="page", required = false, defaultValue = Constant.DEFAULT_PAGE) int page,
                                          @RequestParam(name="size", required=false, defaultValue=Constant.DEFAULT_SIZE) int size){

        logger.info("findAll endpoint....");

        return documentService.findAll(page, size);
    }

    @PostMapping("")
    @ResponseBody
    ResponseModel<Document> save(@RequestBody Document document){
        logger.info("save document endpoint...");
        return documentService.save(document);
    }

    @PostMapping("/checkout")
    @ResponseBody
    ResponseModel<Document> checkoutCertificate(@RequestBody CheckResultRequestDto checkResultRequestDto){
        logger.info("handling post request to find out result...");

        return documentService.checkOutResult(checkResultRequestDto);
    }

    @GetMapping("/clean-db")
    @ResponseBody
    Boolean cleanDb(){
        documentService.cleanDb();

        return true;
    }
}
