package com.dedasp.web.controller.supplier;

import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.service.supplier.SysChangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier")
public class SysChangeController {
    @Resource
    private SysChangeService sysChangeService;

    @GetMapping("/change/{ZGID}/{ZBGID}")
    @Anonymous
    public AjaxResult getChange(@PathVariable String ZGID,@PathVariable String ZBGID) {

        if (ZGID == null || ZBGID == null){
            return AjaxResult.error("参数有误,请检查参数后再试!");
        }

        return sysChangeService.getChange(ZGID,ZBGID);
    }
}
