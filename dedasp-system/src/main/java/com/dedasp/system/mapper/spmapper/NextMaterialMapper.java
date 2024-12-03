package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.FileDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NextMaterialMapper {
    List<FileDTO> getHSLSrcPath(@Param("workFlowGUID")String workFlowGUID);

    void updateQGBAnnex(@Param("workFlowGUID") String workFlowGUID,@Param("盖章结算书") String 盖章结算书);

    void updateInvoiceAnnex(@Param("workFlowGUID") String workFlowGUID,@Param("发票扫描件") String 发票扫描件);

    List<FileDTO> getInvoiceScan(String workFlowGUID);

    List<FileDTO> getSettleAccounts(String workFlowGUID);

    Integer synInvoiceInfo(String workFlowGUID);
}
