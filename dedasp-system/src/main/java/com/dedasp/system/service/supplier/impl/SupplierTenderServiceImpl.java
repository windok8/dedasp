package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.dto.TenderInfoDTO;
import com.dedasp.system.mapper.spmapper.SysTenderMaaper;
import com.dedasp.system.service.supplier.SupplierTenderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SupplierTenderServiceImpl implements SupplierTenderService {
    @Resource
    private SysTenderMaaper sysTenderMaaper;

    /**
     * 获取招标公告相关信息
     * @return
     */
    @Override
    public AjaxResult getTenderInfo() {
        TenderInfoDTO tenderInfoDTO = sysTenderMaaper.getTenderInfoByType();
        return AjaxResult.success(tenderInfoDTO);
    }
}
