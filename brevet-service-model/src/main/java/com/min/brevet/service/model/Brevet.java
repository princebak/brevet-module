package com.min.brevet.service.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Brevet {
    String Nm_Pr;
    String Cd_Pr;
    String Nm_Ctr;
    String Cd_Ctr;
    String Nm_Op;
    String Cd_Op;
    String Nm_Ets;
    String Cd_Ets;
    String Ord_Ets;
    String Cd_Gst;
    String NID;
    String Cd_Cdt;
    String Nms_Cdt;
    String pourcentage;
    String Section;
    String L_N;
    String DateN;
    String Sx;
    String Ecole_Code;
    String LITIGES;
}
