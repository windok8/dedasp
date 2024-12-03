package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysTenderDetail {
    private String ZBQDID;
    private String ZGID;
    private String code;
    private String name;
    private String specification;
    private String unit;
    private BigDecimal quantity;
    private String remark;
}
