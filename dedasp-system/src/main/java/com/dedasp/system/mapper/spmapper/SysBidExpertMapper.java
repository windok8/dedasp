package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysBidExpert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBidExpertMapper {
    List<SysBidExpert> getBidExpertList();
}
