package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysQuestions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAnsweringMapper {
    Integer addQuestion(SysQuestions questions);

    int delQuestionsByIds(@Param("questionsIds") Long[] questionsIds);
}
