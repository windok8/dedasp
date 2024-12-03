package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysBidResult {
    private Long bidResultsId;
    // 项目名称
    private String projectName;
    // 项目编码
    private String projectCode;
    // 招标单位
    private String tenderUnit;
    // 中标单位
    private String bidUnit;
    // 投标报价
    private BigDecimal bidQuotations;
    // 投标日期
    private String bidDate;
    // 开标日期
    private LocalDateTime bidOpenDate;
    // 评标专家
    private String bidEvaluationExperts;
    // 中标金额
    private String bidAmount;
    // 交货期
    private LocalDateTime dateOfDelivery;
    // 评标得分
    private String bidEvaluationScore;
}
