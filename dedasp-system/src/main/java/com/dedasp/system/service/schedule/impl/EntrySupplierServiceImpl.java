package com.dedasp.system.service.schedule.impl;

import com.dedasp.system.mapper.spmapper.schedule.EntrySupplierMapper;
import com.dedasp.system.service.schedule.EntrySupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class EntrySupplierServiceImpl implements EntrySupplierService {
    @Resource
    private EntrySupplierMapper entrySupplierMapper;


    @Override
    public String scheduleSupplier() {

        Integer row = entrySupplierMapper.secheduleSupplier();

        if (row > 0){
            return "同步入库供应商成功:" + "共同步" + row +"条数据";
        }

        return null;
    }
}
