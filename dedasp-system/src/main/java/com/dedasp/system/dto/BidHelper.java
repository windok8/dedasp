package com.dedasp.system.dto;

import com.dedasp.system.domain.spdomain.SysBid;
import com.dedasp.system.domain.spdomain.SysTender2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidHelper {
    private SysTender2 sysTender2;
    private SysBid sysBid;
}
