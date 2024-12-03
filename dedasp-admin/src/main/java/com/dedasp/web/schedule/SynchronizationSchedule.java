package com.dedasp.web.schedule;

import com.dedasp.system.service.schedule.SunshineService;
import com.dedasp.system.service.schedule.SynchronizationScheduleService;
import com.dedasp.system.service.schedule.BidNoticeService;
import com.dedasp.system.service.schedule.EvaluateBidService;
import com.dedasp.system.service.schedule.SynchronizeLevelService;
import com.dedasp.system.service.schedule.TenderScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class SynchronizationSchedule {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizationSchedule.class);

    @Resource
    private SynchronizationScheduleService synchronizationScheduleService;

    @Resource
    private TenderScheduleService tenderScheduleService;

    @Resource
    private SunshineService sunshineService;

    @Resource
    private BidNoticeService bidNoticeService;

    @Resource
    private EvaluateBidService evaluateBidService;

    @Resource
    private SynchronizeLevelService synchronizeLevelService;

    /**
     * 定时同步数据合同
     */
    @Scheduled(initialDelay = 0, fixedDelay = 1000 * 60 * 10)
    public void synchronizeData() {
        //同步工程
        synchronizationScheduleService.startSynchronize();
        //同步材料
        synchronizationScheduleService.materialSynchronize();
        //同步新闻
//        synchronizationScheduleService.newsSynchronize();
        //同步招标公告
        tenderScheduleService.tenderSynchronize();
        //同步阳光招采
        sunshineService.sunshineSynchronize();
        //同步中标公示
        bidNoticeService.bidSynchronize();
        //同步评标结果
        evaluateBidService.evaluteSynchronize();
    }

    @Scheduled(cron = "0 0 0/24 * * ?")
    public void synchronizeSupplierLevel(){
        synchronizeLevelService.levelSynchronize();
    }

}
