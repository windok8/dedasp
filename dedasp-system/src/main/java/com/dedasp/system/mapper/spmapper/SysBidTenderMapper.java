package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBidTenderMapper {
    void addBidTenderRelation(@Param("bidId") Long bidId,@Param("tenderNumber") String tenderNumber);
}
