package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysBidDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBidDetailMapper {
    void addBidDetail(SysBidDetail bidDetail);

    SysBidDetail selectByBidDetail(SysBidDetail bidDetail);
}
