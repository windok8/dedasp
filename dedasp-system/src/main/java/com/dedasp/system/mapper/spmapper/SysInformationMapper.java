package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.Information;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysInformationMapper {
    List<Information> initTable();
}
