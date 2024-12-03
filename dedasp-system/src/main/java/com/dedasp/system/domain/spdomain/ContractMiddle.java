package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractMiddle {
    private Long contractId;
    private String workFlowGUID;
    private Integer isSynchronize;
    private Integer type;
}
