package com.min.brevet.service.api;

import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.ResultsExetat;

import java.util.List;

public interface ResultsExetatService {

    ResponseModel<ResultsExetat> checkOutResult(CheckResultRequestDto checkResultRequestDto);
    ResponseModel<List<ResultsExetat>> findAll(int page, int size);
    ResponseModel<Integer> bulkSave(List<ResultsExetat> resultsExetats);
}
