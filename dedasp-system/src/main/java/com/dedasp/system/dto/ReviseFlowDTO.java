package com.dedasp.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviseFlowDTO {
    private String ApplicationKey;
    private String WorkFlowGUID;
    private String WorkFlowStep;
    private String WorkFlowRestrictUserGUIDList;
}
