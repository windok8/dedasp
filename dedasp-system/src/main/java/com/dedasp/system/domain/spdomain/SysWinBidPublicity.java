package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWinBidPublicity {
    private Long publicityId;
    // 项目名称
    private String projectName;
    // 项目编码
    private String projectCode;
    // 采购人
    private String purchasingAgent;
    // 代理机构
    private String agency;
    // 开标时间
    private LocalDateTime bidDate;
    // 中标候选人
    private String winningCandidate;
    // 中标金额
    private BigDecimal bidAmount;
    // 中标内容
    private String bidContent;
    // 项目负责人
    private String projectLeader;
    // 公示期
    private LocalDateTime periodPublicity;
    // 备注
    private String remark;

}
