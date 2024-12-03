package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWinBid {
    private String ZBSID;
    private String zhaoBiaoName;
    private String biaoTi;
    private String zhaoBiaoCode;
    private String xiangMuName;
    private String xianMuBianHao;
    private String zhongBiaoDanWei;
    private LocalDateTime kaiBiaoDate;
    private String zhongBiaoJia;
    private String ZGID;
    private String gongShiPingTai;
    private LocalDateTime caiGouGongGaoFaBuRiqi;
    private LocalDateTime pingShenRiQi;
    private LocalDateTime gongShiKaiShiDate;
    private LocalDateTime gongShiJieShuDate;
    private String lianXiBuMen;
    private String lianXiDianHua;
    private String TCYY;
    private String danWeiMingCheng;
    private String CGDiZhi;
    private String CGLianXiRen;
    private String CGDianHua;
    private String DLDanWei;
    private String DLDiZhi;
    private String DLLianXiRen;
    private String DLDianHua;
    private String gongShiWenjian;
    private String beiZhu;
    private String chenngJiaoZhuangTai;
}
