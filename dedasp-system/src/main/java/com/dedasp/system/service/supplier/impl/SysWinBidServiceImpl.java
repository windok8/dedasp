package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysWinBid;
import com.dedasp.system.mapper.spmapper.SysWinBidMapper;
import com.dedasp.system.service.supplier.SysWinBidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SysWinBidServiceImpl implements SysWinBidService {
    @Resource
    private SysWinBidMapper sysWinBidMapper;

    @Override
    public AjaxResult getWinBidById(String zgid) {

        SysWinBid sysWinBid = sysWinBidMapper.getWinBidById(zgid);

        return AjaxResult.success(sysWinBid);
    }
}
