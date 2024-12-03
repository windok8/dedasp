package com.dedasp.web.controller.proxy;

import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.common.core.domain.FixResult;
import com.dedasp.system.service.proxy.ContractBillService;
import com.dedasp.system.service.proxy.impl.ProxyRegisterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/contract/bill")
public class ContractBillController {
    private static final Logger logger = LoggerFactory.getLogger(ContractBillController.class);

    @Resource
    private ContractBillService contractBillService;

    /**
     * 同步签章文件
     * @param WorkFlowGUID 唯一标识
     * @param type 类型
     * @return 同步结果
     */
    @Anonymous
    @GetMapping("/project/{WorkFlowGUID}/{type}/{supplierType}")
    public AjaxResult contractFlow(@PathVariable String WorkFlowGUID,@PathVariable Integer type,@PathVariable Integer supplierType){

        if (WorkFlowGUID == null || type == null || supplierType == null){
            return AjaxResult.error("参数不能为空");
        }

        String result = contractBillService.nextFlow(WorkFlowGUID,type,supplierType);
        if (result == null || "".equals(result)) {
            result = "提交审核成功,请耐心等待审批!!!";
        }

        return AjaxResult.success(result);
    }
    /**
     * 【修复】同步签章文件
     * @param WorkFlowGUID 唯一标识
     * @param type 类型
     * @return 同步结果
     */
    @Anonymous
    @GetMapping("/v1/project/{WorkFlowGUID}/{type}/{supplierType}")
    public FixResult contractFlowFix(@PathVariable String WorkFlowGUID, @PathVariable Integer type, @PathVariable Integer supplierType){

        if (WorkFlowGUID == null || type == null || supplierType == null){
            return FixResult.error("参数不能为空");
        }
        return contractBillService.nextFlowFix(WorkFlowGUID,type,supplierType);
    }


}
