package com.dedasp.web.schedule;

import com.dedasp.system.service.schedule.TenderScheduleService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReversalSechedule {

    @Resource
    private TenderScheduleService tenderScheduleService;

    @Scheduled(initialDelay = 0,fixedDelay = 1000 * 60 *10)
    public void tenderSechedule(){
        tenderScheduleService.resveralTenderSchedule();
    }

}
