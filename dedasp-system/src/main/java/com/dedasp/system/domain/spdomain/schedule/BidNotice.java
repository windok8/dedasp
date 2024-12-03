package com.dedasp.system.domain.spdomain.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hpsf.Decimal;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidNotice {
    private String BidId;
    private String ZhaoBiaoName;
    private String ZhaoBiaoCode;
    private String XiangMuName;
    private String XianMuBianHao;
    private String ZhongBiaoDanWei;
    private LocalDateTime KaiBiaoDate;
    private Decimal ZhongBiaoJia;
    private String ZGID;
    private String GongShiPingTai;
    private LocalDateTime CaiGouGongGaoFaBuRiqi;
    private LocalDateTime PingShenRiQi;
    private String TuiJianGongYingShang;
    private LocalDateTime GongShiKaiShiDate;
    private LocalDateTime GongShiJieShuDate;
    private String LianXiBuMen;
    private String LianXiDianHua;
    private String DanWeiMingCheng;
    private String CGDiZhi;
    private String CGLianXiRen;
    private String CGDianHua;
    private String DLDanWei;
    private String DLDiZhi;
    private String DLLianXiRen;
    private String DLDianHua;
    private String GongShiWenjian;
    private String WorkFlowGUID;
    private Integer type;
}
