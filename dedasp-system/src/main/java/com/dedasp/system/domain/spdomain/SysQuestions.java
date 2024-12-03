package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysQuestions {
    /*id*/
    private Long questionId;
    /*招标主题*/
    private String tenderTheme;
    /*提问人*/
    private String questioner;
    /*提问内容*/
    private String questionContent;
    /*提问时间*/
    private LocalDateTime questionDate;
    /*附件的路径*/
    private List<SysAnnex> annexes;
    /*回复实体类*/
    private SysReply sysReply;

    public SysQuestions(String tenderTheme, String questioner, String questionContent, LocalDateTime questionDate, List<SysAnnex> annexes) {
        this.tenderTheme = tenderTheme;
        this.questioner = questioner;
        this.questionContent = questionContent;
        this.questionDate = questionDate;
        this.annexes = annexes;
    }
}
