package com.dedasp.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowDTO {
    private String ApplicationRSAPublicKey;
    private String ApplicationKey;
    private String URL;
    private String WorkFlowGUID;
    private String WorkFlowStep;
    private String WorkFlowRestrictUserGUIDList;

}
