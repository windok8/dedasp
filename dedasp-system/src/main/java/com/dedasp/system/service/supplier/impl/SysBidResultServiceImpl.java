package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.HSLBidResult;
import com.dedasp.system.mapper.spmapper.SysBidResultMapper;
import com.dedasp.system.service.supplier.SysBidResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("supplierSysBidResultServiceImpl")
@Transactional
public class SysBidResultServiceImpl implements SysBidResultService {
    @Resource
    private SysBidResultMapper sysBidResultMapper;


    /**
     * 根据id查询评标结果
     * @param ZGID
     * @return
     */
    @Override
    public AjaxResult getBidById(String ZGID) {
        HSLBidResult hslBidResult = sysBidResultMapper.getBidById(ZGID);

        return AjaxResult.success(hslBidResult);
    }
}
