package com.dedasp.web.controller.supplier;

import com.alibaba.fastjson2.JSON;
import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysTender;
import com.dedasp.system.service.supplier.SysBidResultService;
import com.dedasp.system.service.supplier.SysTenderService;
import com.dedasp.system.service.supplier.SysWinBidService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier/tender")
public class TenderController {
    @Resource
    private SysTenderService sysTenderService;
    @Resource(name = "supplierSysBidResultServiceImpl")
    private SysBidResultService sysBidResultService;
    @Resource
    private SysWinBidService sysWinBidService;

    /**
     * 根据id获取招标公告
     * @param ZGID
     * @return
     */
    @Anonymous
    @GetMapping("/list/{ZGID}/{type}")
    public AjaxResult getTenderList(@PathVariable String ZGID, @PathVariable Integer type){
        if ((type != null && ZGID != null) && type == 1){
            return sysTenderService.getTenderById(ZGID);
        }else if (type == 2){
            return sysBidResultService.getBidById(ZGID);
        }else if (type == 3){
            return sysWinBidService.getWinBidById(ZGID);
        }

       return AjaxResult.error("数据错误,请检查传入的数据是否正确!");
    }

    @Anonymous
    @PostMapping("/flow")
    public AjaxResult startTenderFlow(String id){
        if (id == null || "".equals(id)){
            return AjaxResult.error("请传入正确的数据!!");
        }

        return sysTenderService.startTenderFlow(id);
    }
}
