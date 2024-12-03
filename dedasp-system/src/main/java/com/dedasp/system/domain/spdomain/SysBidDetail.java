package com.dedasp.system.domain.spdomain;

import com.dedasp.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysBidDetail {
    @Excel(name = "投标明细序号",prompt = "投标明细编号")
    private Long bidDetailId;
    @Excel(name = "资源名称")
    private String resourceName;
    @Excel(name = "规格型号")
    private String specificationModel;
    @Excel(name = "单位")
    private String unit;
    @Excel(name = "数量")
    private Integer quantity;
    @Excel(name = "不含税单价")
    private BigDecimal noTaxesUnitPrice;
    @Excel(name = "税率")
    private Integer tax;
    @Excel(name = "不含税单价合计")
    private BigDecimal noTaxesUnitPriceSum;
    @Excel(name = "备注")
    private String remark;
}
