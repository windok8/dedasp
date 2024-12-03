package com.dedasp.system.service.proxy.impl;

import cn.hutool.json.JSONUtil;
import com.dedasp.common.core.domain.FixResult;
import com.dedasp.common.enums.ResponseCode;
import com.dedasp.common.utils.dedasp.AnnexUtils;
import com.dedasp.common.utils.dedasp.SynchUtils;
import com.dedasp.system.domain.spdomain.FileDTO;
import com.dedasp.system.dto.FlowDTO;
import com.dedasp.system.mapper.spmapper.NextFlowAnnexPathMapper;
import com.dedasp.system.mapper.spmapper.NextMaterialMapper;
import com.dedasp.system.mapper.spmapper.NextProjectMapper;
import com.dedasp.system.service.proxy.ContractBillService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dedasp.common.constant.SystemConstants.DEDA_BASE_PATH;
import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_KEY;
import static com.dedasp.common.utils.http.HttpClientHelperUtils.APPLICATION_RSA_PUBLIC_KEY;

@Service
public class ContractBillServiceImpl implements ContractBillService {

    private static final Logger log = LoggerFactory.getLogger(ContractBillServiceImpl.class);
    @Resource
    private NextProjectMapper nextProjectMapper;

    @Resource
    private NextMaterialMapper nextMaterialMapper;
    @Resource
    private NextFlowAnnexPathMapper nextFlowAnnexPathMapper;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;


    /**
     * 流程下一步的方法
     *
     * @param workFlowGUID
     * @param type
     * @return
     */
    @Override
    public String nextFlow(String workFlowGUID, Integer type, Integer supplierType) {
        String result = "";

        if (type == 1) {
            List<FileDTO> HSLSrcPath = new ArrayList<>();
            if (supplierType == 1) {
                HSLSrcPath = nextProjectMapper.getHSLSrcPath(workFlowGUID);
            } else if (supplierType == 2) {
                HSLSrcPath = nextProjectMapper.getSettleAccounts(workFlowGUID);
            }

            if (HSLSrcPath == null || HSLSrcPath.size() == 0) {
                return "盖章结算书未上传!";
            }

            List<String> filePathList = new ArrayList<>();

            Iterator<FileDTO> iterator = HSLSrcPath.iterator();
            while (iterator.hasNext()) {
                FileDTO next = iterator.next();

                String srcFile = "";
                if (supplierType == 1) {
                    srcFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), true);
                } else if (supplierType == 2) {
                    srcFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), false);
                }

                String fileName = srcFile.substring(srcFile.lastIndexOf("/") + 1);

                StringBuffer destFile = new StringBuffer(SynchUtils.getDestDedaPath(fileName));
                filePathList.add(destFile.toString());

                SynchUtils.downloadAnnex(srcFile, destFile);
            }

            StringBuffer filePath = new StringBuffer();
            Iterator<String> filePathIterator = filePathList.iterator();
            while (filePathIterator.hasNext()) {

                String next = filePathIterator.next();

                boolean isLastElement = !filePathIterator.hasNext();

                int length = DEDA_BASE_PATH.length();
                String filePath1 = next.substring(length + 1);
                String 盖章结算书 = AnnexUtils.handlerAnnexPath(filePath1);

                if (isLastElement) {
                    filePath.append(盖章结算书 + ",");
                } else {
                    filePath.append(盖章结算书);
                }

            }

            nextProjectMapper.updateQGBAnnex(workFlowGUID, filePath.toString());

            List<FileDTO> invoiceSrcPath = nextProjectMapper.getInvoiceScan(workFlowGUID);

            if (invoiceSrcPath == null && invoiceSrcPath.size() == 0) {
                return "发票未上传!!";
            }

            List<String> invoicePathList = new ArrayList<>();

            Iterator<FileDTO> invoiceIterator = invoiceSrcPath.iterator();
            while (invoiceIterator.hasNext()) {
                FileDTO next = invoiceIterator.next();

                String srcInvoiceFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), false);

                String fileName = srcInvoiceFile.substring(srcInvoiceFile.lastIndexOf("/") + 1);

                StringBuffer destInvoiceFile = new StringBuffer(SynchUtils.getDestDedaPath(fileName));
                invoicePathList.add(destInvoiceFile.toString());

                SynchUtils.downloadAnnex(srcInvoiceFile, destInvoiceFile);
            }

            StringBuffer invoicePath = new StringBuffer();
            Iterator<String> invoicePathIterator = invoicePathList.iterator();
            while (invoicePathIterator.hasNext()) {

                String next = invoicePathIterator.next();

                boolean isLastElement = !invoicePathIterator.hasNext();

                int length = DEDA_BASE_PATH.length();
                String filePath1 = next.substring(length + 1);

                String 发票扫描件 = AnnexUtils.handlerAnnexPath(filePath1);

                if (isLastElement) {
                    invoicePath.append(发票扫描件 + ",");
                } else {

                    invoicePath.append(发票扫描件);
                }

            }

            nextProjectMapper.updateInvoiceAnnex(workFlowGUID, invoicePath.toString());

            boolean success = synInvoiceInfo(type, workFlowGUID);

            if (success) {
                result = invokeCom(workFlowGUID, type);
            }

        } else if (type == 2) {
            List<FileDTO> HSLSrcPath = new ArrayList<>();
            if (supplierType == 1) {
                HSLSrcPath = nextMaterialMapper.getHSLSrcPath(workFlowGUID);
            } else if (supplierType == 2) {
                HSLSrcPath = nextMaterialMapper.getSettleAccounts(workFlowGUID);
            }

            if (HSLSrcPath == null || HSLSrcPath.size() == 0) {
                return "盖章结算书未上传!";
            }

            List<String> filePathList = new ArrayList<>();

            Iterator<FileDTO> iterator = HSLSrcPath.iterator();
            while (iterator.hasNext()) {
                FileDTO next = iterator.next();

                String srcFile = "";
                if (supplierType == 1) {
                    srcFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), true);
                } else if (supplierType == 2) {
                    srcFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), false);
                }

                String fileName = srcFile.substring(srcFile.lastIndexOf("/") + 1);

                StringBuffer destFile = new StringBuffer(SynchUtils.getDestDedaPath(fileName));
                filePathList.add(destFile.toString());

                SynchUtils.downloadAnnex(srcFile, destFile);
            }

            StringBuffer filePath = new StringBuffer();
            Iterator<String> filePathIterator = filePathList.iterator();
            while (filePathIterator.hasNext()) {

                String next = filePathIterator.next();

                boolean isLastElement = !filePathIterator.hasNext();

                int length = DEDA_BASE_PATH.length();
                String filePath1 = next.substring(length + 1);
                String 盖章结算书 = AnnexUtils.handlerAnnexPath(filePath1);

                if (isLastElement) {
                    filePath.append(盖章结算书 + ",");
                } else {

                    filePath.append(盖章结算书);
                }

            }

            nextMaterialMapper.updateQGBAnnex(workFlowGUID, filePath.toString());

            List<FileDTO> invoiceSrcPath = nextMaterialMapper.getInvoiceScan(workFlowGUID);

            if (invoiceSrcPath == null && invoiceSrcPath.size() == 0) {
                return "发票未上传!!";
            }

            List<String> invoicePathList = new ArrayList<>();

            Iterator<FileDTO> invoiceIterator = invoiceSrcPath.iterator();
            while (invoiceIterator.hasNext()) {
                FileDTO next = invoiceIterator.next();

                String srcInvoiceFile = SynchUtils.getOriginHSLPath(next.getPath(), next.getId(), false);

                String fileName = srcInvoiceFile.substring(srcInvoiceFile.lastIndexOf("/") + 1);

                StringBuffer destInvoiceFile = new StringBuffer(SynchUtils.getDestDedaPath(fileName));
                invoicePathList.add(destInvoiceFile.toString());

                SynchUtils.downloadAnnex(srcInvoiceFile, destInvoiceFile);
            }

            StringBuffer invoicePath = new StringBuffer();
            Iterator<String> invoicePathIterator = invoicePathList.iterator();
            while (invoicePathIterator.hasNext()) {

                String next = invoicePathIterator.next();

                boolean isLastElement = !invoicePathIterator.hasNext();

                int length = DEDA_BASE_PATH.length();
                String filePath1 = next.substring(length + 1);
                String 发票扫描件 = AnnexUtils.handlerAnnexPath(filePath1);

                if (isLastElement) {
                    invoicePath.append(发票扫描件 + ",");
                } else {

                    invoicePath.append(发票扫描件);
                }

            }

            nextMaterialMapper.updateInvoiceAnnex(workFlowGUID, invoicePath.toString());

            boolean success = synInvoiceInfo(type, workFlowGUID);

            if (success) {
                result = invokeCom(workFlowGUID, type);
            }

        }

        return result;
    }

    /**
     * 【修复】流程下一步的方法
     *
     * @param workFlowGUID
     * @param type
     * @return type 1:工程合同结算 2:材料合同结算
     */
    @Override
    public FixResult nextFlowFix(String workFlowGUID, Integer type, Integer supplierType) {
        if (type == 1 || type == 2) {
            log.info("【检查 - 提交 - workFlowGUID】" + workFlowGUID + (type == 1?"工程合同结算":"材料合同结算"));
            List<FileDTO> HSLSrcPath = getAnnexPath(type, supplierType, workFlowGUID, 1);
            List<FileDTO> invoiceSrcPath = getAnnexPath(type, supplierType, workFlowGUID, 2);
            if (HSLSrcPath == null || HSLSrcPath.isEmpty()) {
                return FixResult.error(ResponseCode.STATEMENT_NOT_UPLOADED);
            }
            if (invoiceSrcPath == null || invoiceSrcPath.isEmpty()) {
                return FixResult.error(ResponseCode.BILL_NOT_UPLOADED);
            }
            //  盖章结算书 路径
            String hslAnnexPaths = processAnnexFiles(HSLSrcPath, supplierType, true, type);
            updateQGBAnnex(type, 1, workFlowGUID, hslAnnexPaths);
            //  发票扫描件 路径
            String invoiceAnnexPaths = processAnnexFiles(invoiceSrcPath, supplierType, false, type);
            updateQGBAnnex(type, 2, workFlowGUID, invoiceAnnexPaths);

            boolean success = synInvoiceInfo(type, workFlowGUID);
            updateHSLFlowStep(type, workFlowGUID);
            if (success) {
                log.info("【检查 - 【完成】 发票同步 - workFlowGUID】" + workFlowGUID + (type == 1?"工程合同结算":"材料合同结算"));
            }else {
                log.info("【检查 - 发票同步 【失败】 - workFlowGUID】" + workFlowGUID + (type == 1?"工程合同结算":"材料合同结算"));
            }
            String result = invokeCom(workFlowGUID, type);
            if (result == null || result.equals("")) return FixResult.success(ResponseCode.SUCCESS);
            return FixResult.error(ResponseCode.WORKSTEP_UPDTAE_FAILED);
        }
        return FixResult.error("非法请求");
    }

    private void updateHSLFlowStep(Integer type, String workFlowGUID) {
        log.info("【检查 - 流程进度同步 - workFlowGUID】" + workFlowGUID + (type == 1?"工程合同结算":"材料合同结算"));
        String tableName = type == 1 ? "Suplier.dbo.project_contract_billing" : "Suplier.dbo.CaiLiaoHeTongJieSuan";
        nextFlowAnnexPathMapper.updateHSLStep(tableName, workFlowGUID);
    }

    private List<FileDTO> getAnnexPath(Integer type, Integer supplierType, String workFlowGUID, Integer annexType) {
        String attchNodeID = annexType == 2 ? "InvoiceInfo" : supplierType == 1 ? "bill_notice" : "GZJieSuanDan";
        String tableName = type == 1 ? "Suplier.dbo.project_contract_billing" : "Suplier.dbo.CaiLiaoHeTongJieSuan";
        String ownerColumn = type == 1 ? "PCB_ID" : "CLHT_ID";
        if (supplierType == 1) {
            return nextFlowAnnexPathMapper.getStatementPath(tableName, ownerColumn, workFlowGUID, attchNodeID);
        } else {
            return nextFlowAnnexPathMapper.getStatementPath2(tableName, ownerColumn, workFlowGUID, attchNodeID);
        }
    }

    private void updateQGBAnnex(Integer type, Integer annexType, String workFlowGUID, String annexPaths) {
        String tableName = type == 1 ? "分包结算主表" : "材料合同结算主表";
        String columnName = annexType == 1 ? "盖章结算书" : "发票扫描件";
        nextFlowAnnexPathMapper.updateQGBAnnex(tableName, columnName, annexPaths, workFlowGUID);

    }

    private String processAnnexFiles(List<FileDTO> fileDTOList, Integer supplierType, boolean isHSL, Integer type) {
        List<String> filePathList = new ArrayList<>();
        boolean check = isHSL ? (supplierType == 1 ? true : false) : false;
        for (FileDTO fileDTO : fileDTOList) {
            String srcFile = SynchUtils.getOriginHSLPath(fileDTO.getPath(), fileDTO.getId(), check);
            String fileName = srcFile.substring(srcFile.lastIndexOf("/") + 1);
            StringBuffer destFile = new StringBuffer(SynchUtils.getDestDedaPath(fileName));
            filePathList.add(destFile.toString());
            SynchUtils.downloadAnnex(srcFile, destFile);
        }

        return mergeFilePaths(filePathList);
    }


    private String mergeFilePaths(List<String> filePathList) {
        StringBuffer mergedPaths = new StringBuffer();

        for (int i = 0; i < filePathList.size(); i++) {
            String filePath = filePathList.get(i);
            int length = DEDA_BASE_PATH.length();
            String relativePath = filePath.substring(length + 1);
            String processedPath = AnnexUtils.handlerAnnexPath(relativePath);

            mergedPaths.append(processedPath);
            if (i < filePathList.size() - 1) {
                mergedPaths.append(",");
            }
        }

        return mergedPaths.toString();
    }


    private String invokeCom(String workFlowGUID, Integer type) {

        String WorkFlowStep = "发起人确认";
        String WorkFlowRestrictUserGUIDList = "";
        String URL = "http://wx.dedajiaojian.com:8090/CRM/RestAPI/WorkFlow.aspx?action=UpdateWorkFlowStep";
        FlowDTO flowDTO = new FlowDTO(APPLICATION_RSA_PUBLIC_KEY, APPLICATION_KEY, URL, workFlowGUID, WorkFlowStep, WorkFlowRestrictUserGUIDList);
        String jsonStr = JSONUtil.toJsonStr(flowDTO);

        ComThread.InitSTA(false);
        ActiveXComponent dotnetCom = null;

        dotnetCom = new ActiveXComponent("BiddingAgencyWorkFlow.BiddingAgency");
        Variant var = Dispatch.call(dotnetCom, "WorkFlowAdd", jsonStr);

        return var.toString();

    }

    //同步发票信息
    private boolean synInvoiceInfo(Integer type, String workFlowGUID) {
        boolean flag = false;

        if (type == null) {
            return flag;
        } else if (type == 1) {
            Integer row = nextProjectMapper.synInvoiceInfo(workFlowGUID);
            if (row > 0) {
                flag = true;
            }
        } else if (type == 2) {
            Integer row = nextMaterialMapper.synInvoiceInfo(workFlowGUID);
            if (row > 0) {
                flag = true;
            }
        }

        return flag;
    }
}
