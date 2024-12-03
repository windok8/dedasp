package com.dedasp.system.mapper.spmapper.schedule;

import com.dedasp.system.domain.spdomain.ContractMiddle;
import com.dedasp.system.domain.spdomain.SysAttechment;
import com.dedasp.system.dto.SunshineDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SunshineMapper {
    List<ContractMiddle> getMiddleData();

    Integer startSynchronize(@Param("middleList") List<ContractMiddle> middleList);

    void updateSynchronizeStatus(@Param("middleList") List<ContractMiddle> middleList);

    List<SunshineDTO> getPathList(@Param("middleList") List<ContractMiddle> middleList);

    List<SysAttechment> insertHSLAnnex(@Param("desPathList") List<SysAttechment> desPathList);
}
