package com.dedasp.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JACOBHelper {
    private String ApplicationRSAPublicKey;
    private String ApplicationKey;
    private String URL;
    private String WorkFlowType;
    private String WorkFlowStep;
    private Integer AgencyID;
    private Integer DepartmentID;
    private String Initiateuserguid;
    private String RestrictUserGuidList;
    private String DataDictionaryJSON;

}
