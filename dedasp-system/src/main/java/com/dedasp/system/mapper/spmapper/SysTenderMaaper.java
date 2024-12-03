package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysTender;
import com.dedasp.system.domain.spdomain.SysTender2;
import com.dedasp.system.dto.TenderInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysTenderMaaper {
    List<SysTender2> selectTenderList(@Param("type") Integer type);

    TenderInfoDTO getTenderInfoByType();

    Integer addTender(SysTender sysTender);

    SysTender getTendreById(String ZGID);
}
