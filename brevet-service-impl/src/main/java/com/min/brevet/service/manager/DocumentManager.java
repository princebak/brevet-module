package com.min.brevet.service.manager;

import com.min.brevet.service.api.DocumentService;
import com.min.brevet.service.api.ResponseModel;
import com.min.brevet.service.api.ResultsExetatService;
import com.min.brevet.service.model.CheckDocumentDTO;
import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.Document;
import com.min.brevet.service.model.ResultsExetat;
import com.min.brevet.service.repository.DocumentRepository;
import com.min.brevet.service.repository.ResultsExetatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentManager implements DocumentService {

    private final Logger logger = LoggerFactory.getLogger(DocumentManager.class);
    private DocumentRepository repository;

    public DocumentManager(DocumentRepository repository){
        this.repository = repository;
    }

    @Override
    public ResponseModel<List<Document>> findAll(int page, int size){
        ResponseModel<List<Document>> response = new ResponseModel();
        try{
            Page<Document> documents = repository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "generated" ));

            response.setStatus(200);
            response.setContent(documents.toList());
        }catch(Exception ex){
            response.setError(true);
            response.setErrorMessage(ex.getMessage());
            response.setStatus(500);
        }

        return response;
    }
    @Override
    public ResponseModel<Document> save(Document document) {
        ResponseModel<Document> response = new ResponseModel();
        try{

            Optional<Document> documentExist = repository.findByResults_Id(document.getResults().getId());
            if(documentExist.isPresent())
            {
                logger.info("document all ready exist, do not duplicate...");
                response.setError(false);
                response.setStatus(200);
                response.setContent(documentExist.get());
            }
            else{
                logger.info("saving document...");
                //save the document to mongo
                repository.save(document);
                //try retrieve the document saved
                ResponseModel<List<Document>> documents = findAll(0, 1);

                //fill the response fields
                response.setError(documents.isError());
                response.setErrorMessage(documents.getErrorMessage());
                response.setStatus(documents.getStatus());

                //test if there's an error occurred when try retrieving the document saved
                if(!documents.isError()){
                    //there's no error, then fill the response content with the document saved
                    response.setContent(documents.getContent().get(0));
                }
            }

        }catch(Exception ex){
            //error occurred
            logger.error("error occurred : " + ex.getMessage());
            response.setError(true);
            response.setErrorMessage(ex.getMessage());
            response.setStatus(500);
        }

        return response;

    }
    @Override
    public ResponseModel<Document> authenticateDocument(CheckDocumentDTO checkDocumentDTO) {

        ResponseModel<Document> responseModel = new ResponseModel();
        try {
            Optional<Document> document = repository.findByRef(checkDocumentDTO.getDocRef());

            if(document.isPresent()){
                responseModel.setContent(document.get());
                responseModel.setStatus(200);
            }
            else{
                responseModel.setError(true);
                responseModel.setErrorMessage("Document not exist");
                responseModel.setStatus(404);
            }
        }
        catch(Exception ex){
            responseModel.setError(true);
            responseModel.setErrorMessage("Document not exist");
            responseModel.setStatus(500);
        }

        return responseModel;
    }

    @Override
    public ResponseModel<Document> checkOutResult(CheckResultRequestDto checkResultRequestDto) {
        ResponseModel<Document> responseModel = new ResponseModel();
        try {
            Optional<Document> document = repository.findByRef(checkResultRequestDto.getCode14());
            // findAll().stream().filter(result -> result.getCdCdt().equals(checkResultRequestDto.getCode14())).find                          killFirst() ; // repository.findBynID(checkResultRequestDto.getCode());

            if (document.isPresent()) {
                responseModel.setStatus(200);
                responseModel.setContent(document.get());
            } else {
                responseModel.setStatus(404);
                responseModel.setError(true);
                responseModel.setErrorMessage("no such document found with the code/ref : " + checkResultRequestDto.getCode14());
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

    @Override
    public void cleanDb() {
        repository.deleteAll();
    }
}
