package com.min.brevet.service.api;

import lombok.Data;

@Data
public class ResponseModel<T>  {

    boolean error;
    String errorMessage;
    int status;
    T content;

    public ResponseModel(boolean error, String errorMessage, int status, T content){
        this.error = error;
        this.errorMessage = errorMessage;
        this.status = status;
        this.content = content;
    }

    public ResponseModel(){
        error = false;
        errorMessage = "";
        status = 0;
        content = null;
    }
}
