package com.dedasp.system.service.proxy.impl;

import com.dedasp.system.domain.spdomain.SysBidResult;
import com.dedasp.system.mapper.spmapper.SysBidResultMapper;
import com.dedasp.system.service.proxy.SysBidResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysBidResultServiceImpl implements SysBidResultService {
    @Resource
    private SysBidResultMapper sysBidResultMapper;

    @Override
    public List<SysBidResult> getEvaluationList() {

        return sysBidResultMapper.getEvaluationList();
    }
}
