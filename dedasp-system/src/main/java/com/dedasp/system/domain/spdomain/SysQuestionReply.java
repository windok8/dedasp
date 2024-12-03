package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysQuestionReply {
    private Long questionId;
    private Long replyId;
}
