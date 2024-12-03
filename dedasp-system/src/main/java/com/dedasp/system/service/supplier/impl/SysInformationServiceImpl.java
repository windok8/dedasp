package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.Information;
import com.dedasp.system.mapper.spmapper.SysInformationMapper;
import com.dedasp.system.service.supplier.SysInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysInformationServiceImpl implements SysInformationService {

    @Resource
    private SysInformationMapper sysInformationMapper;

    /**
     * 获取资料数据
     * @return 资料列表
     */
    @Override
    public AjaxResult initTable() {

        List<Information> informations = sysInformationMapper.initTable();

        return AjaxResult.success(informations);
    }
}
