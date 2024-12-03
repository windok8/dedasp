package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysBid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBidMapper {
    Integer addBid(SysBid sysBid);

    SysBid getLastBid(@Param("tenderNumber") String tenderNumber,@Param("userId") Long userId);

    void updateBidByBidId(Long bidId);

    List<SysBid> getBidDetailInformation(String tenderNumber);
}
