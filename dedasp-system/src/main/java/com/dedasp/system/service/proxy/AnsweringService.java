package com.dedasp.system.service.proxy;

import com.dedasp.system.domain.spdomain.SysQuestions;
import com.dedasp.system.domain.spdomain.SysReply;

import java.util.List;

public interface AnsweringService {
    List<SysQuestions> getList();

    int insertReply(SysReply sysReply,Long questionsId);

    int deleteQuestionsByIds(Long[] questionsId);

    int updateReply(SysReply sysReply);
}
