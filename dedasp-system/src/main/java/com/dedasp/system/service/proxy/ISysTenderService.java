package com.dedasp.system.service.proxy;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysTender;
import com.dedasp.system.domain.spdomain.SysTender2;

import java.util.List;

public interface ISysTenderService {
    List<SysTender2> selectTenderList(Integer type);

    AjaxResult addTender(SysTender sysTender);
}
