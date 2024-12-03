package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysBidExpert {
    private Long AgencyID;
    private Long DepartmentID;
    private String 专家编号;
    private String 姓名;
    private String 性别;
    private String 专家类别;
    private String 职称;
    private String 技术专业;
    private String 出生日期;
    private String 工作时间;
    private String 联系电话;
    private String 文化程度;
    private String 工作单位;
    private String 区域;
    private String 备注;
    private String 附件;
}
