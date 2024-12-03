package com.dedasp.system.service.proxy.impl;

import com.dedasp.system.domain.spdomain.SysQuestions;
import com.dedasp.system.domain.spdomain.SysReply;
import com.dedasp.system.mapper.spmapper.*;
import com.dedasp.system.service.proxy.AnsweringService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AnsweringServiceImpl implements AnsweringService {
    @Resource
    private AnsweringMapper answeringMapper;
    @Resource
    private QuestionReplyMapper questionReplyMapper;
    @Resource
    private AnnexMapper annexMapper;
    @Resource
    private QuestionAnnexMapper questionAnnexMapper;
    @Resource
    private SupplierAnsweringMapper supplierAnsweringMapper;


    @Override
    public List<SysQuestions> getList() {

        List<SysQuestions> list = answeringMapper.getList();

        list.forEach(item -> {
           if (item.getSysReply() == null){
               item.setSysReply(new SysReply(0));
           }
        });

        return list;
    }

    /**
     * 添加回复
     * @param sysReply 回复实体类
     * @param questionsId 问题id
     * @return
     */
    @Override
    public int insertReply(SysReply sysReply,Long questionsId) {
        sysReply.setReplyDate(LocalDateTime.now());
        int row = answeringMapper.insertReply(sysReply);
        questionReplyMapper.insertQuestionReply(questionsId,sysReply.getReplyId());

        return row;
    }

    /**
     * 修改回复
     * @param sysReply 更新回复的数据
     * @return
     */
    @Override
    public int updateReply(SysReply sysReply) {
        sysReply.setReplyDate(LocalDateTime.now());
        return answeringMapper.updateReply(sysReply);
    }

    /**
     * 删除问题
     * @param questionsIds 问题id数组
     * @return
     */
    @Override
    public int deleteQuestionsByIds(Long[] questionsIds) {
        List<Long> replyIds = questionReplyMapper.getReplyIdsByQuestionsId(questionsIds);

        if (replyIds.size() > 0){
            answeringMapper.delReplyByReplyId(replyIds);
            questionReplyMapper.delQuestionReplyByQuestionsId(questionsIds);
        }

        List<Long> annexIds = questionAnnexMapper.getAnnexIdsByQuestionsId(questionsIds);

        if (annexIds.size() > 0){
            annexMapper.delAnnexByAnnexId(annexIds);
            questionAnnexMapper.delQuestionAnnexByQuestionsId(questionsIds);
        }

        return supplierAnsweringMapper.delQuestionsByIds(questionsIds);
    }
}
