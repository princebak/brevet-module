package com.min.brevet.service.controller;

import com.min.brevet.service.api.ResponseModel;
import com.min.brevet.service.api.ResultsExetatService;
import com.min.brevet.service.common.util.Constant;
import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.ResultsExetat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/results")
public class ResultsExetatController {

    private Logger logger = LoggerFactory.getLogger(ResultsExetatController.class);

    private ResultsExetatService resultsExetatService;
    public ResultsExetatController(ResultsExetatService resultsExetatService){
        this.resultsExetatService = resultsExetatService;
    }

    @GetMapping("")
    @ResponseBody
    ResponseModel<List<ResultsExetat>> findAll(@RequestParam(name = "page", required = false, defaultValue = Constant.DEFAULT_PAGE) int page,
                                               @RequestParam(name="size", required = false, defaultValue = Constant.DEFAULT_SIZE) int size){

        return resultsExetatService.findAll(page, size);
    }

    @PostMapping("/checkout")
    @ResponseBody
    ResponseModel<ResultsExetat> checkoutCertificate(@RequestBody CheckResultRequestDto checkResultRequestDto){
        logger.info("handling post request to find out result...");

        return resultsExetatService.checkOutResult(checkResultRequestDto);
    }

    @PostMapping("/bulk-save")
    @ResponseBody
    ResponseModel<Integer> bulkSave(@RequestBody List<ResultsExetat> resultsExetats){
        logger.info("bulkSave endpoint ...");
        return resultsExetatService.bulkSave(resultsExetats);
    }
}
