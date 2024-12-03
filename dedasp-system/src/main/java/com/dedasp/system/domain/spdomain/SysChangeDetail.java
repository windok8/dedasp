package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysChangeDetail {
    private String ZBMID;
    private String tiaoMu;
    private String yuanNeiRong;
    private String bianGengHouNeiRong;
}
