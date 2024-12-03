package com.dedasp.system.mapper;

import com.dedasp.system.domain.spdomain.SysProxy;
import com.dedasp.system.dto.ProxyRegisterHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyRegisterMapper {
    /**
     * 更新流程附件
     */
    Integer updateFlowAnnex(ProxyRegisterHelper proxyRegisterHelper);
}
