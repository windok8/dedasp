package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysChanger;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysChangeMapper {

    SysChanger getChange(@Param("ZGID") String ZGID,@Param("ZBGID") String ZBGID);
}
