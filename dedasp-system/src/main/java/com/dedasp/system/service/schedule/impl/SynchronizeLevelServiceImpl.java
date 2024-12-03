package com.dedasp.system.service.schedule.impl;

import com.dedasp.system.mapper.spmapper.schedule.SynchronizeLevelMapper;
import com.dedasp.system.service.schedule.SynchronizeLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SynchronizeLevelServiceImpl implements SynchronizeLevelService {
    @Resource
    private SynchronizeLevelMapper synchronizeLevelMapper;

    @Override
    public String levelSynchronize() {
        Integer row = synchronizeLevelMapper.updateLevel();

        if (row > 0){
            return "同步供应商等级成功,共同步" + row + "条数据";
        }
        return "暂无数据需要同步";
    }
}
