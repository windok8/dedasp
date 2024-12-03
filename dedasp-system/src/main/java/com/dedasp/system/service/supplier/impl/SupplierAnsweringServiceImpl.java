package com.dedasp.system.service.supplier.impl;

import com.dedasp.system.domain.spdomain.SysAnnex;
import com.dedasp.system.domain.spdomain.SysQuestions;
import com.dedasp.system.mapper.spmapper.AnnexMapper;
import com.dedasp.system.mapper.spmapper.QuestionAnnexMapper;
import com.dedasp.system.mapper.spmapper.SupplierAnsweringMapper;
import com.dedasp.system.service.supplier.SupplierAnsweringService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierAnsweringServiceImpl implements SupplierAnsweringService {
    @Resource
    private SupplierAnsweringMapper supplierAnsweringMapper;
    @Resource
    private AnnexMapper annexMapper;
    @Resource
    private QuestionAnnexMapper questionAnnexMapper;

    @Override
    public Integer addQuestion(SysQuestions questions) {
        List<Long> annexIds = new ArrayList<>();
        Integer row = supplierAnsweringMapper.addQuestion(questions);
        if (questions.getAnnexes() != null && questions.getAnnexes().size() > 0){
            for(SysAnnex annex : questions.getAnnexes()){
                annexMapper.addAnnex(annex);
                annexIds.add(annex.getAnnexId());
            }
            for (Long annexId : annexIds){
                questionAnnexMapper.addAnnexQuestion(annexId, questions.getQuestionId());
            }
        }

        return row;
    }
}
