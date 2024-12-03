package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysAnnex;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnexMapper {

    void addAnnex(SysAnnex annex);

    void delAnnexByAnnexId(@Param("annexIds") List<Long> annexIds);
}
