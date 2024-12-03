package com.dedasp.system.service.supplier;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.dto.BidHelper;

public interface SysBidService {
    AjaxResult addBid(BidHelper bidHelper);

    AjaxResult getLastBid(String tenderNumber);

    AjaxResult getBidDetailInformation(String tenderNumber);
}
