package com.min.brevet.service.api;

import com.min.brevet.service.model.CheckDocumentDTO;
import com.min.brevet.service.model.CheckResultRequestDto;
import com.min.brevet.service.model.Document;
import com.min.brevet.service.model.ResultsExetat;

import java.util.List;

public interface DocumentService {

    ResponseModel<List<Document>> findAll(int page, int size);
    ResponseModel<Document> save(Document document);
    ResponseModel<Document> authenticateDocument(CheckDocumentDTO checkDocumentDTO);

    ResponseModel<Document> checkOutResult(CheckResultRequestDto checkResultRequestDto);

    void cleanDb();
}
