package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysChanger;
import com.dedasp.system.mapper.spmapper.SysChangeMapper;
import com.dedasp.system.service.supplier.SysChangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysChangeServiceImpl implements SysChangeService {
    @Resource
    private SysChangeMapper sysChangeMapper;

    /**
     * 获取变更信息
     * @return
     */
    @Override
    public AjaxResult getChange(String ZGID,String ZBGID) {

        if (ZGID == null || ZBGID == null){
            return AjaxResult.error("参数有误,请检查参数后再试!");
        }

        SysChanger sysChange = sysChangeMapper.getChange(ZGID,ZBGID);

        return AjaxResult.success(sysChange);
    }
}
