package com.dedasp.web.schedule.hsl;

import com.dedasp.system.service.schedule.EntrySupplierService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EnteySupplierSchedule {
    @Resource
    private EntrySupplierService entrySupplierService;

    /**
     * 将红树林中已入库的供应商信息同步到红树林
     */
    @Scheduled(initialDelay = 0,fixedDelay = 1000 * 60 * 60 * 24)
    public void scheduleSupplier() {
        entrySupplierService.scheduleSupplier();
    }

}
