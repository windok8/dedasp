package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String ZALID;
    private String workFlowGUID;
    private String newsImage;
    private String title;
    private String knowledgeType;
    private String fileClass;
    private String filePath;
    private String fileName;
    private Boolean isPush;
    private LocalDateTime fabuTime;
    private String lable;
    private String briefInfo;
    private String content;
    private LocalDateTime rekeaseTime;
    private String rekeaseUser;
    private Float average;
    private Integer clickCount;
    private String bigClass;

    public News(String workFlowGUID) {
        this.workFlowGUID = workFlowGUID;
    }

    public News(String workFlowGUID, String newsImage) {
        this.workFlowGUID = workFlowGUID;
        this.newsImage = newsImage;
    }

    public News(String workFlowGUID, String title, String knowledgeType, String fileClass, String filePath, String fileName, Boolean isPush, LocalDateTime fabuTime, String lable, String briefInfo, String content, LocalDateTime rekeaseTime, String rekeaseUser, Float average, Integer clickCount, String ZALID, String bigClass) {
        this.workFlowGUID = workFlowGUID;
        this.title = title;
        this.knowledgeType = knowledgeType;
        this.fileClass = fileClass;
        this.filePath = filePath;
        this.fileName = fileName;
        this.isPush = isPush;
        this.fabuTime = fabuTime;
        this.lable = lable;
        this.briefInfo = briefInfo;
        this.content = content;
        this.rekeaseTime = rekeaseTime;
        this.rekeaseUser = rekeaseUser;
        this.average = average;
        this.clickCount = clickCount;
        this.ZALID = ZALID;
        this.bigClass = bigClass;
    }
}
