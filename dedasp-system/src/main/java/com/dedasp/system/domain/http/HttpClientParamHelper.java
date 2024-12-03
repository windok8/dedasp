package com.dedasp.system.domain.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpClientParamHelper {
    private String ApplicationKey;
    private String TimeStamp;
    private String Token;
    private String WorkFlowType;
    private String WorkFlowStep;
    private Integer AgencyID;
    private Integer DepartmentID;
    private String Initiateuserguid;
    private String RestrictUserGuidList;
    private String DataDictionaryJSON;
}
