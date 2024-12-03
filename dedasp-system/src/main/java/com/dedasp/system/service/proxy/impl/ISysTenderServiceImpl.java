package com.dedasp.system.service.proxy.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysTender;
import com.dedasp.system.domain.spdomain.SysTender2;
import com.dedasp.system.domain.spdomain.SysTenderDetail;
import com.dedasp.system.mapper.spmapper.SysTenderDetailMapper;
import com.dedasp.system.mapper.spmapper.SysTenderDetailRelationshipMapper;
import com.dedasp.system.mapper.spmapper.SysTenderMaaper;
import com.dedasp.system.service.proxy.ISysTenderService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ISysTenderServiceImpl implements ISysTenderService {
    @Resource
    private SysTenderMaaper sysTenderMaaper;

    @Resource
    private SysTenderDetailMapper sysTenderDetailMapper;

    @Resource
    private SysTenderDetailRelationshipMapper sysTenderDetailRelationshipMapper;

    /**
     * 根据招标公告类型查询
     *
     * @param type
     * @return
     */
    @Override
    public List<SysTender2> selectTenderList(Integer type) {

        List<SysTender2> list = sysTenderMaaper.selectTenderList(type);
        list.forEach(sysTender -> {
            String annex = StringEscapeUtils.unescapeHtml4(sysTender.getAnnex());
            sysTender.setAnnex(annex);
        });

        return list;
    }

    /**
     * 新增招标公告
     * @return
     */
    @Override
    public AjaxResult addTender(SysTender sysTender) {

        long timeMillis = System.currentTimeMillis();
        String tenderNumber = "TD_"+timeMillis;
        sysTender.setContactPhone(tenderNumber);

        Integer row = sysTenderMaaper.addTender(sysTender);

        if (row < 1){
            return AjaxResult.error("添加招标公告失败");
        }

        List<SysTenderDetail> sysTenderDetails = sysTender.getSysTenderDetails();

        if (sysTenderDetails != null && sysTenderDetails.size()>0){
            sysTenderDetails.forEach(sysTenderDetail -> {
                sysTenderDetailMapper.addTenderDetail(sysTenderDetail);
                sysTenderDetailRelationshipMapper.addTenderDetailRelationship(101L,tenderNumber);
            });
        }

        return AjaxResult.success("添加招标公告成功");
    }
}
