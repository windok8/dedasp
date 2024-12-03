package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysReply {
    /*id*/
    private Long replyId;
    /*回复人*/
    private String replier;
    /*回复内容*/
    private String replyContent;
    /*回复状态*/
    private Integer replyState;
    /*回复时间*/
    private LocalDateTime replyDate;

    public SysReply(Integer replyState) {
        this.replyState = replyState;
    }
}
