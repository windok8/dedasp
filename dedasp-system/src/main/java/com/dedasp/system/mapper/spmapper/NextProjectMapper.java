package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.FileDTO;
import jdk.vm.ci.meta.Assumptions;
import org.apache.ibatis.annotations.Param;

import java.io.File;
import java.util.List;

public interface NextProjectMapper {
    List<FileDTO> getPathFix(@Param("workFlowGUID") String workFlowGUID,@Param("attchNodeID") String attchNodeID);
    void updateFlowStep(@Param("type") int type,@Param("flowStep") String flowStep,@Param("workFlowGUID") String workFlowGUID);
    List<FileDTO> getHSLSrcPath(String workFlowGUID);

    void updateQGBAnnex(@Param("workFlowGUID") String workFlowGUID,@Param("盖章结算书") String 盖章结算书);

    List<FileDTO> getInvoiceScan(String workFlowGUID);

    void updateInvoiceAnnex(@Param("workFlowGUID") String workFlowGUID,@Param("发票扫描件") String 发票扫描件);

    List<FileDTO> getSettleAccounts(String workFlowGUID);

    Integer synInvoiceInfo(String workFlowGUID);
}
