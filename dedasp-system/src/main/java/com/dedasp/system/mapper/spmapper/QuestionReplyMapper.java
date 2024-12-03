package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionReplyMapper {
    void insertQuestionReply(@Param("questionId") Long questionId,@Param("replyId") Long replyId);

    void delQuestionReplyByQuestionsId(@Param("questionsIds") Long[] questionsIds);

    List<Long> getReplyIdsByQuestionsId(@Param("questionsIds") Long[] questionsIds);
}
