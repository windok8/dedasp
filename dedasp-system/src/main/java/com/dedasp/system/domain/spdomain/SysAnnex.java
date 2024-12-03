package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAnnex {
    private Long annexId;
    private String annexPath;
    private String annexName;
    private String annexSuffix;

    public SysAnnex(String annexPath, String annexName, String annexSuffix) {
        this.annexPath = annexPath;
        this.annexName = annexName;
        this.annexSuffix = annexSuffix;
    }

}
