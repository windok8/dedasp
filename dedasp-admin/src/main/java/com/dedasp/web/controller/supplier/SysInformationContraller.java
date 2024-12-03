package com.dedasp.web.controller.supplier;

import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.service.supplier.SysInformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier/information")
public class SysInformationContraller {

    @Resource
    private SysInformationService sysInformationService;

    /**
     * 获取资料数据
     * @return
     */
    @Anonymous
    @GetMapping("/initTable")
    public AjaxResult getInformation() {
        return sysInformationService.initTable();
    }
}
