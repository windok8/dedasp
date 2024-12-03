package com.dedasp.web.controller.supplier;

import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.service.schedule.EntrySupplierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier")
public class StorageController {

    @Resource
    private EntrySupplierService entrySupplierService;

    @Anonymous
    @GetMapping("/storage")
    public AjaxResult storageSuplier(){

        String s = entrySupplierService.scheduleSupplier();

        return AjaxResult.success(s);
    }
}
