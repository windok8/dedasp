package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAnnexMapper {

    void addAnnexQuestion(@Param("annexId") Long annexId,@Param("questionId") Long questionId);

    void delQuestionAnnexByQuestionsId(@Param("questionsIds") Long[] questionsIds);

    List<Long> getAnnexIdsByQuestionsId(@Param("questionsIds") Long[] questionsIds);
}
