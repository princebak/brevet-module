package com.min.brevet.service.api;

import com.min.common.service.model.AbstractRequestResponse;
import java.util.List;

public class ResponseModel<T> extends AbstractRequestResponse<T> {

    public ResponseModel(int totalPages, long totalElements, List<T> content){
        super(totalPages, totalElements, content);
    }
}
