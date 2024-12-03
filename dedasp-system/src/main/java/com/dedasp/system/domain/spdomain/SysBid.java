package com.dedasp.system.domain.spdomain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysBid {
    private Long bidId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime bidDate;
    private Integer bidFrequency;
    private Integer latestDid;
    private List<SysBidDetail> bidDetailList;
    private String supplierNumber;
    private String supplierName;
}
