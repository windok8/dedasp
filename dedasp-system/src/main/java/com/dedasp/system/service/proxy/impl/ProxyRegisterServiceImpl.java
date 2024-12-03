package com.dedasp.system.service.proxy.impl;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.LogFactory;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.common.utils.StringUtils;
import com.dedasp.common.utils.dedasp.AnnexUtils;
import com.dedasp.system.domain.spdomain.SysProxy;
import com.dedasp.system.dto.JACOBHelper;
import com.dedasp.system.dto.ProxyRegisterHelper;
import com.dedasp.system.mapper.ProxyRegisterMapper;
import com.dedasp.system.service.proxy.ProxyRegisterService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_KEY;
import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_RSA_PUBLIC_KEY;

@Service
@Transactional
public class ProxyRegisterServiceImpl implements ProxyRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(ProxyRegisterServiceImpl.class);

    @Resource
    private ProxyRegisterMapper proxyRegisterMapper;

    /**
     * 添加流程附件
     */
    @Override
    public Integer updateFlowAnnex(ProxyRegisterHelper proxyRegisterHelper) {

        return proxyRegisterMapper.updateFlowAnnex(proxyRegisterHelper);

    }


    /**
     * 调用com组件
     * @return
     */
    @Override
    public String invokeComModule(SysProxy sysProxy) {

        String postData = JSONUtil.toJsonStr(sysProxy);

        String WorkFlowType = "招标代理申请表";
        String WorkFlowStep = "资质审查";
        Integer AgencyID = 26;
        Integer DepartmentID = 108;
        String Initiateuserguid = "00000000-0000-0000-0000-000000000000";
        String RestrictUserGuidList = "";
        String URL = "http://wx.dedajiaojian.com:8090/CRM/RestAPI/WorkFlow.aspx?action=CreateWorkFlow";

        JACOBHelper jacobHelper = new JACOBHelper(APPLICATION_RSA_PUBLIC_KEY, APPLICATION_KEY, URL, WorkFlowType, WorkFlowStep, AgencyID, DepartmentID, Initiateuserguid, RestrictUserGuidList, postData);
        String jsonStr = JSONUtil.toJsonStr(jacobHelper);

        ComThread.InitSTA(false);
        ActiveXComponent dotnetCom = null;

        dotnetCom = new ActiveXComponent("BiddingAgencyWorkFlow.BiddingAgency");
        Variant var = Dispatch.call(dotnetCom, "WorkFlowAdd", jsonStr);

        return var.toString();
    }

    /**
     * 更新附件
     * @param jsonStr
     * @param sysProxy
     * @return
     */
    @Override
    public AjaxResult updateAnnex(String jsonStr, SysProxy sysProxy) {
        if (StringUtils.isNotNull(sysProxy.get营业执照正本()) && !"".equals(sysProxy.get营业执照正本())){
            sysProxy.set营业执照正本(AnnexUtils.handlerAnnexPath(sysProxy.get营业执照正本()));
        }

        if (StringUtils.isNotNull(sysProxy.get营业执照副本()) && !"".equals(sysProxy.get营业执照副本())){
            sysProxy.set营业执照副本(AnnexUtils.handlerAnnexPath(sysProxy.get营业执照副本()));
        }

        if (StringUtils.isNotNull(sysProxy.get其他证件()) && !"".equals(sysProxy.get其他证件())){
            sysProxy.set其他证件(AnnexUtils.handlerAnnexPath(sysProxy.get其他证件()));
        }

        if (StringUtils.isNotNull(sysProxy.get法人身份证复印件()) && !"".equals(sysProxy.get法人身份证复印件())){
            sysProxy.set法人身份证复印件(AnnexUtils.handlerAnnexPath(sysProxy.get法人身份证复印件()));
        }

        ProxyRegisterHelper proxyRegisterHelper = JSONUtil.toBean(jsonStr, ProxyRegisterHelper.class);
        if (proxyRegisterHelper.getIsValid()) {

            proxyRegisterHelper.set营业执照正本(sysProxy.get营业执照正本());
            proxyRegisterHelper.set营业执照副本(sysProxy.get营业执照副本());
            proxyRegisterHelper.set法人身份证复印件(sysProxy.get法人身份证复印件());
            proxyRegisterHelper.set其他证件(sysProxy.get其他证件());
            Integer row = updateFlowAnnex(proxyRegisterHelper);

            if (row < 1){
                logger.error("附件更新失败,请重新上传附件");
                return AjaxResult.error("附件添加失败,请重新上传附件");
            }
        }else {
            logger.error("调用com组件失败");
            return AjaxResult.error("发起流程失败,请稍后重试");
        }

        return AjaxResult.success("申请成功,请耐心等待审核");
    }
}
