package com.dedasp.system.service.supplier.impl;

import cn.hutool.json.JSONUtil;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.system.domain.spdomain.SysTender;
import com.dedasp.system.dto.JACOBHelper;
import com.dedasp.system.mapper.spmapper.SysTenderMaaper;
import com.dedasp.system.service.supplier.SysTenderService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_KEY;
import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_RSA_PUBLIC_KEY;

@Service
@Transactional
public class SysTenderServiceImpl implements SysTenderService {
    @Resource
    private SysTenderMaaper sysTenderMaaper;

    @Override
    public AjaxResult getTenderById(String ZGID) {

        SysTender sysTender = sysTenderMaaper.getTendreById(ZGID);

        return AjaxResult.success(sysTender);
    }

    /**
     * 发起招标公告流程
     * @return
     */
    @Override
    public AjaxResult startTenderFlow(String id) {
        SysTender tendre = sysTenderMaaper.getTendreById(id);


        return AjaxResult.success("");
    }

    private String invokeTenderCom(SysTender sysTender){
        String postData = JSONUtil.toJsonStr(sysTender);

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
}
