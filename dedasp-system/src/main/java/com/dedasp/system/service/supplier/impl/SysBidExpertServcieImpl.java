package com.dedasp.system.service.supplier.impl;

import com.dedasp.system.domain.spdomain.SysBidExpert;
import com.dedasp.system.mapper.spmapper.SysBidExpertMapper;
import com.dedasp.system.service.supplier.SysBidExpertService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysBidExpertServcieImpl implements SysBidExpertService {
    @Resource
    private SysBidExpertMapper bidExpertMapper;

    @Override
    public List<SysBidExpert> getBidExpertList() {
        return bidExpertMapper.getBidExpertList();
    }
}
