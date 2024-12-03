package com.dedasp.system.service.proxy;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysProxy;
import com.dedasp.system.dto.ProxyRegisterHelper;

public interface ProxyRegisterService {
    /**
     * 更新流程附件信息
     *
     */
    Integer updateFlowAnnex(ProxyRegisterHelper proxyRegisterHelper);

    String invokeComModule(SysProxy sysProxy);

    AjaxResult updateAnnex(String jsonStr, SysProxy sysProxy);
}
