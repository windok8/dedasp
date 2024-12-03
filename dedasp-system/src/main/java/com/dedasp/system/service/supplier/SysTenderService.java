package com.dedasp.system.service.supplier;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysTender;

public interface SysTenderService {
    AjaxResult getTenderById(String ZGID);

    AjaxResult startTenderFlow(String id);
}
