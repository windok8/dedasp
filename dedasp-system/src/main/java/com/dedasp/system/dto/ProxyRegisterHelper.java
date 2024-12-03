package com.dedasp.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyRegisterHelper {
    private Integer AgencyID;
    private List<String> ApprovedWorkFlowStepList;
    private Integer DepartmentID;
    private String ExtraDataDictionary;
    private LocalDateTime InitiateDate;
    private String Initiateuserguid;
    private Boolean IsInitiateWorkFlowInstance;
    private Boolean IsValid;
    private Integer OrganizationID;
    private List<String> RestrictJumpWorkFlowStepList;
    private String SubSystemCode;
    private String WorkFlowCategory;
    private String WorkFlowGUID;
    private String WorkFlowInstanceCode;
    private Integer WorkFlowInstanceID;
    private Boolean WorkFlowIsRestrictUser;
    private List<String> WorkFlowRestrictUserGUIDList;
    private Boolean WorkFlowRestrictUserNeedAllApprove;
    private List<String> WorkFlowStep;
    private Boolean WorkFlowStepIsMulti;
    private String WorkFlowType;
    private String 营业执照正本;
    private String 营业执照副本;
    private String 法人身份证复印件;
    private String 其他证件;
}
