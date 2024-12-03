package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysQuestions;
import com.dedasp.system.domain.spdomain.SysReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnsweringMapper {

    List<SysQuestions> getList();

    int insertReply(@Param("sysReply") SysReply sysReply);

    void delReplyByReplyId(@Param("replyIds") List<Long> replyIds);

    int updateReply(@Param("sysReply") SysReply sysReply);
}
