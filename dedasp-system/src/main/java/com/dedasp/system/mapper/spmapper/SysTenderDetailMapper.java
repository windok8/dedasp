package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysTenderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface SysTenderDetailMapper {

    void addTenderDetail(SysTenderDetail sysTenderDetail);
}
