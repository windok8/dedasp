package com.dedasp.web.controller.proxy;

import com.dedasp.common.utils.StringUtils;
import com.dedasp.system.service.proxy.ProxyRegisterService;
import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.controller.BaseController;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysProxy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


//@RestController
public class RegisterController extends BaseController {


    /*@Resource
    private ProxyRegisterService proxyRegisterService;

    @Anonymous
    @PostMapping("/proxy/register")
    public AjaxResult proxyRegister(@RequestBody SysProxy sysProxy){

        if (!(StringUtils.isNotNull(sysProxy.get营业执照副本()) && !"".equals(sysProxy.get营业执照副本()))){
            logger.debug("营业执照副本不能为空");
            return AjaxResult.error("营业执照副本不能为空");
        }

        //调用com组件
        String jsonStr = proxyRegisterService.invokeComModule(sysProxy);

        //更新附件
        return proxyRegisterService.updateAnnex(jsonStr,sysProxy);
    }*/

}
