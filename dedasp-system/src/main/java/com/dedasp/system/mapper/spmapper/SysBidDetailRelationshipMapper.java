package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBidDetailRelationshipMapper {
    void addBidDetailRelation(@Param("bidDetailId") Long bidDetailId,@Param("bidId") Long bidId);
}
