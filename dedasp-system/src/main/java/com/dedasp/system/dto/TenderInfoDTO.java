package com.dedasp.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenderInfoDTO {
    private Integer openTenderCount;
    private Integer invitationTenderCount;
    private Integer requestForQuotationCount;
    private Integer singleSourceCount;
}
