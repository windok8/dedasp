package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HSLBidResultDetail {
    private String PJGID;
    private String ZBHID;
    private String danWeiName;
    private String lianXiRen;
    private String lianXiFangShi;
    private BigDecimal chuShiBaoJia;
    private BigDecimal touBiaoJia;
    private String gongQi;
    private Float deFen;
}
