package com.dedasp.system.service.proxy;

import com.dedasp.common.core.domain.FixResult;

public interface ContractBillService {
    String nextFlow(String workFlowGUID, Integer type,Integer supplierType);
    FixResult nextFlowFix(String workFlowGUID, Integer type, Integer supplierType);
}
