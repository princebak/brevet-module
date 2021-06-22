package uk.co.icbs.sgc.service.api;

import uk.co.icbs.common.service.model.AbstractRequestResponse;
import java.util.List;

public class ResponseModel<T> extends AbstractRequestResponse<T> {

    public ResponseModel(int totalPages, long totalElements, List<T> content){
        super(totalPages, totalElements, content);
    }
}
