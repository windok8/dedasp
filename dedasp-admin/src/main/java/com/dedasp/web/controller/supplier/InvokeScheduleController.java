package com.dedasp.web.controller.supplier;

import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.service.schedule.SynchronizationScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier/invoke")
public class InvokeScheduleController {

    @Resource
    private SynchronizationScheduleService synchronizationScheduleService;

    /**
     * 手动同步合同信息
     * @param type 类型
     * @return 同步结果
     */
    @Anonymous
    @PutMapping("/schedule/{type}")
    public AjaxResult invokeSchedule(@PathVariable Integer type){

        if (type == null){
            return AjaxResult.error("参数错误");
        }else if (type == 1){
            String information = synchronizationScheduleService.startSynchronize();
            return AjaxResult.success(information);
        }else if (type == 2){
            String information = synchronizationScheduleService.materialSynchronize();
            return AjaxResult.success(information);
        }else {
            return AjaxResult.error("参数错误");
        }

    }
}
