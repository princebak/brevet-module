package com.min.brevet.service.repository;

import com.min.brevet.service.model.ResultsExetat;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResultsExetatRepository extends MongoRepository<ResultsExetat,String> {

    /**
     * @param cdCdt
     * @return
     */
    Optional<ResultsExetat> findByCdCdt(String  cdCdt);
    Optional<ResultsExetat> findBySx(String  sx);
    Optional<ResultsExetat> findByCdOp(String  sx);
    Optional<ResultsExetat> findByLn(String  sx);
    Optional<ResultsExetat> findBynID(String  sx);
//    List<ResultsExetat> findBySx(String sex);
    List<ResultsExetat> findByNmsCdt( String nms_cdt);
//    List<ResultsExetat> findByNm_Pr(String provinceName);
//    List<ResultsExetat> findByNm_Op(String optionName);

}
