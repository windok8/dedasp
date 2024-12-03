package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysSupplierManager;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierManagerMapper {

    SysSupplierManager getAccountInfo(Long userId);
}
