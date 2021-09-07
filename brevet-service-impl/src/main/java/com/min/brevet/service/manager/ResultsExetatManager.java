package com.min.brevet.service.manager;

import com.min.brevet.service.api.ResponseModel;
import com.min.brevet.service.api.ResultsExetatService;
import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.ResultsExetat;
import com.min.brevet.service.repository.ResultsExetatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultsExetatManager implements ResultsExetatService {

    private Logger logger = LoggerFactory.getLogger(ResultsExetatManager.class);

    private ResultsExetatRepository repository;

    public ResultsExetatManager(ResultsExetatRepository repository){
        this.repository = repository;
    }

    @Override
    public ResponseModel<List<ResultsExetat>> findAll(int page, int size){
        ResponseModel<List<ResultsExetat>> responseModel = new ResponseModel();

        try{
            logger.info("find all exetat results-from mongo...");
            List<ResultsExetat> results = repository.findAll();

            responseModel.setStatus(200);
            responseModel.setContent(results);

            logger.info("tobe continued...");
        }
        catch(Exception ex){
            logger.error("error occurred : " + ex.getMessage());
            responseModel.setStatus(500);
            responseModel.setError(true);
            responseModel.setErrorMessage(ex.getMessage());
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Integer> bulkSave(List<ResultsExetat> resultsExetats) {
        ResponseModel<Integer> responseModel = new ResponseModel();
        try {
            repository.saveAll(resultsExetats);

            responseModel.setStatus(200);
            responseModel.setContent(resultsExetats.size());
        }
        catch(Exception ex){
            logger.error("error occurred : " + ex.getMessage());
            responseModel.setStatus(500);
            responseModel.setError(true);
            responseModel.setErrorMessage(ex.getMessage());
        }

        return responseModel;
    }

    @Override
    public ResponseModel<ResultsExetat> checkOutResult(CheckResultRequestDto checkResultRequestDto) {

        ResponseModel<ResultsExetat> responseModel = new ResponseModel();
        try {
            Optional<ResultsExetat> resultExetat = repository.findAll().stream().filter(result -> result.getCdCdt().equals(checkResultRequestDto.getCode14())).findFirst() ; // repository.findBynID(checkResultRequestDto.getCode());
            if (resultExetat.isPresent()) {
                responseModel.setStatus(200);
                responseModel.setContent(resultExetat.get());
            } else {
                responseModel.setStatus(404);
                responseModel.setError(true);
                responseModel.setErrorMessage("no such student found with the 14-code : " + checkResultRequestDto.getCode14());
            }
        }
        catch(Exception ex){
            logger.error("error occurred : " + ex.getMessage());
            responseModel.setStatus(500);
            responseModel.setError(true);
            responseModel.setErrorMessage(ex.getMessage());
        }

        return responseModel;
    }
}
