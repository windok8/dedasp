package com.dedasp.system.service.supplier;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysWinBid;

public interface SysWinBidService {
    AjaxResult getWinBidById(String zgid);
}
