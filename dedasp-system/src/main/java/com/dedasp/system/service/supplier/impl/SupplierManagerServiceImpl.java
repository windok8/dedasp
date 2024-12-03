package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.common.utils.SecurityUtils;
import com.dedasp.system.domain.spdomain.SysSupplierManager;
import com.dedasp.system.mapper.spmapper.SupplierManagerMapper;
import com.dedasp.system.service.supplier.SupplierManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SupplierManagerServiceImpl implements SupplierManagerService {
    @Resource
    private SupplierManagerMapper supplierManagerMapper;

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public AjaxResult getAccountInfo() {
        SysSupplierManager sysSupplierManager = supplierManagerMapper.getAccountInfo(SecurityUtils.getUserId());
        return AjaxResult.success(sysSupplierManager);
    }
}
