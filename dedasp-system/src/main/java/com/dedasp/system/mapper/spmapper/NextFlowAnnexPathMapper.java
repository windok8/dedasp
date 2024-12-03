package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.FileDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NextFlowAnnexPathMapper {

    List<FileDTO> getStatementPath(@Param("tableName") String tableName,
                                   @Param("ownerColumn") String ownerColumn,
                                   @Param("workFlowGUID") String workFlowGUID,
                                   @Param("attchNodeID") String attchNodeID);
    List<FileDTO> getStatementPath2(@Param("tableName") String tableName,
                                   @Param("ownerColumn") String ownerColumn,
                                   @Param("workFlowGUID") String workFlowGUID,
                                   @Param("attchNodeID") String attchNodeID);
    void updateQGBAnnex(@Param("tableName") String tableName,
                        @Param("columnName") String columnName,
                        @Param("columnValue") String columnValue,
                        @Param("workFlowGUID") String workFlowGUID);
    void updateHSLStep(@Param("tableName") String tableName,@Param("workFlowGUID") String workFlowGUID);

}
