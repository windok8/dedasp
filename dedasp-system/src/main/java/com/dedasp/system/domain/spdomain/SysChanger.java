package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysChanger {
    private String ZBGID;
    private String ZGID;
    private String gongGaoZhuangTai;
    private String tenderName;
    private String tenderCode;
    private String bianGengBianHao;
    private LocalDateTime kaiBiaoDate;
    private String yuanGongNeiRong;
    private String bianGengNeiRong;
    private String bianGengYuanYin;
    private String CGDanWei;
    private String DDDanWei;
    private String caiGouDiZhi;
    private String daiLiDiZhi;
    private String CGLianXiRen;
    private String DLLianXiRen;
    private String CGDianHua;
    private String DLDianHua;
    private String CGChuanZhen;
    private String DLChuanZhen;
    private String CGYouBian;
    private String DDYouBian;
    private String biaoTi;
}
