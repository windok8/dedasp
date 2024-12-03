package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.HSLBidResult;
import com.dedasp.system.domain.spdomain.SysBidResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBidResultMapper {
    List<SysBidResult> getEvaluationList();

    HSLBidResult getBidById(String PJGID);
}
