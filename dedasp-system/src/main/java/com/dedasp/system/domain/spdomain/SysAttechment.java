package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAttechment {
    private String attchID;
    private String attchOwner;
    private String attchName;
    private String attchPath;
    private String attchType;
    private Long attchSize;
    private LocalDateTime attchCreatedTime;
    private String attchNote;
    private String attchCreatorID;
    private boolean attchIsLock;
    private String attchNodeID;
}
