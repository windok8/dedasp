package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HSLBidResult {
    private String PJGID;
    private String prjName;
    private String prjCode;
    private String name;
    private String code;
    private LocalDateTime caiGouGongGaoFaBuDate;
    private LocalDateTime pingShenDate;
    private LocalDateTime gongShiKaiShiRiQi;
    private LocalDateTime gongShiJieShuRiQi;
    private String lianXiBuMen;
    private String lianXiDianHua;
    private String CGDanWei;
    private String CADiZhi;
    private String CGLianXiRen;
    private String CGDianHua;
    private String DLDanWei;
    private String DLDiZhi;
    private String DLLianXiRen;
    private String DLDianHua;
    private String tuiJianZhongBiao;
    private List<HSLBidResultDetail> details;
}
