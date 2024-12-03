package com.dedasp.system.service.proxy.impl;

import com.dedasp.system.domain.spdomain.SysWinBidPublicity;
import com.dedasp.system.mapper.spmapper.SysWinBidPublicityMapper;
import com.dedasp.system.service.proxy.SysWinBidPublicityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysWinBidPublicityServiceImpl implements SysWinBidPublicityService {
    @Resource
    private SysWinBidPublicityMapper winBidPublicityMapper;

    @Override
    public List<SysWinBidPublicity> getPublicityList() {
        return winBidPublicityMapper.getPublicityList();
    }
}
