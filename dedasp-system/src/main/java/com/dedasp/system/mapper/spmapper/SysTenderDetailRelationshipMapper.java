package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;

public interface SysTenderDetailRelationshipMapper {
    void addTenderDetailRelationship(@Param("tenderDetailId") Long tenderDetailId,@Param("tenderNumber") String tenderNumber);
}
