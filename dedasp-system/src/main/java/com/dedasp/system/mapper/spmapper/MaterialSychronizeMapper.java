package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.ContractMiddle;
import com.dedasp.system.domain.spdomain.SysAttechment;
import com.dedasp.system.dto.SychnoizedDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialSychronizeMapper {

    List<ContractMiddle> getMiddleData();

    Integer startSynchronize(@Param("middleList") List<ContractMiddle> middleList);

    void updateSynchronizeStatus(@Param("middleList") List<ContractMiddle> middleList);

    List<SychnoizedDTO> getPathList(@Param("middleList") List<ContractMiddle> middleList);

    void insertHSLAnnex(SysAttechment sysAttechment);

    String getAttchID();
}