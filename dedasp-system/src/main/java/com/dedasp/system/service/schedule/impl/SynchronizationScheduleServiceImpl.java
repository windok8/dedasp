package com.dedasp.system.service.schedule.impl;

import com.dedasp.common.utils.dedasp.SynchUtils;
import com.dedasp.system.domain.spdomain.ContractMiddle;
import com.dedasp.system.domain.spdomain.News;
import com.dedasp.system.domain.spdomain.SysAttechment;
import com.dedasp.system.dto.SychnoizedDTO;
import com.dedasp.system.mapper.spmapper.MaterialSychronizeMapper;
import com.dedasp.system.mapper.spmapper.SynchronizationScheduleMapper;
import com.dedasp.system.mapper.spmapper.SysNewsMapper;
import com.dedasp.system.service.schedule.SynchronizationScheduleService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hpsf.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dedasp.common.constant.SystemConstants.*;

@Service
@Transactional
public class SynchronizationScheduleServiceImpl implements SynchronizationScheduleService {

    private static final Logger log = LoggerFactory.getLogger(SynchronizationScheduleServiceImpl.class);
    @Resource
    private SynchronizationScheduleMapper synchronizationScheduleMapper;

    @Resource
    private MaterialSychronizeMapper materialSychronizeMapper;

    @Resource
    private SysNewsMapper sysNewsMapper;


    private String getLogTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }


    /**
     * 开始同步
     */
    @Override
    public String startSynchronize() {

        List<ContractMiddle> middleList = synchronizationScheduleMapper.getMiddleData();

        if (middleList.size() < 1 || middleList.isEmpty()) {
            return null;
        }

        Integer row = synchronizationScheduleMapper.startSynchronize(middleList);
        log.info("同步数据条数：" + row);
        synchronizationScheduleMapper.updateSynchronizeStatus(middleList);

        List<SychnoizedDTO> pathList = synchronizationScheduleMapper.getPathList(middleList);
        if (!(pathList.size() < 1 || pathList.isEmpty())) {
            List<SychnoizedDTO> filePathList = new CopyOnWriteArrayList<>();
            Iterator<SychnoizedDTO> iterator = pathList.iterator();
            while (iterator.hasNext()){
                SychnoizedDTO dto = iterator.next();
                String html4 = StringEscapeUtils.unescapeHtml4(dto.getAnnex());
                Pattern pattern = Pattern.compile("<a href=\"(.*?)\".*?>(.*?)</a>");
                Matcher matcher = pattern.matcher(html4);
                if (matcher.find()) {
                    String filePath = matcher.group(1);
                    if (filePath.matches(".*\\\\.*")) {
                        filePath = filePath.replaceAll("\\\\", "/");
                    }

                    SychnoizedDTO sychnoizedDTO = new SychnoizedDTO();
                    sychnoizedDTO.setWorkFlowGUID(dto.getWorkFlowGUID());
                    sychnoizedDTO.setAnnex(filePath);
                    filePathList.add(sychnoizedDTO);
                }
            }

            List<SysAttechment> desPathList = new CopyOnWriteArrayList<>();

            Iterator<SychnoizedDTO> iterator1 = filePathList.iterator();
            while (iterator1.hasNext()){
                SychnoizedDTO dto = iterator1.next();

                String srcfile = DEDA_BASE_PATH + "/" + dto.getAnnex();

                File file = new File(srcfile);
                long size = file.length();
                int index = dto.getAnnex().lastIndexOf("/");
                String s = dto.getAnnex().substring(index + 1);
                String suffex = s.substring(s.lastIndexOf(".") + 1);
                String fileName = s.substring(0,s.lastIndexOf("."));
                StringBuffer rootPath = new StringBuffer();
                rootPath.append(DEDA_HSL_PATH);
                rootPath.append("/Attechment");

                SysAttechment attechment = new SysAttechment();

                StringBuffer destfile = new StringBuffer(rootPath + "/"+ UUID.randomUUID() + "/" + fileName + "." + suffex);
                SynchUtils.downloadAnnex(srcfile, destfile);
                String hslName = destfile.substring(DEDA_HSL_PATH.length());

                attechment.setAttchOwner(dto.getWorkFlowGUID());
                attechment.setAttchName(fileName + "." + suffex);
                attechment.setAttchPath(hslName);
                attechment.setAttchType(suffex);
                attechment.setAttchSize(size);
                attechment.setAttchCreatorID("admin");
                attechment.setAttchNodeID("bill_notice");
                desPathList.add(attechment);
            }

            Iterator<SysAttechment> iterator2 = desPathList.iterator();
            while (iterator2.hasNext()){
                SysAttechment next = iterator2.next();
                synchronizationScheduleMapper.insertHSLAnnex(next);
            }

            return "数据同步成功,共同步" + row + "条数据";
        }else {
            return "请上传结算通知单";
        }
    }

    /**
     * 同步材料合同
     * @return
     */
    @Override
    public String materialSynchronize() {
        List<ContractMiddle> middleList = materialSychronizeMapper.getMiddleData();

        if (middleList.size() < 1 || middleList.isEmpty()) {
            return null;
        }

        Integer row = materialSychronizeMapper.startSynchronize(middleList);
        log.info("同步数据条数：" + row);
        materialSychronizeMapper.updateSynchronizeStatus(middleList);

        List<SychnoizedDTO> pathList = materialSychronizeMapper.getPathList(middleList);
        if (!(pathList.size() < 1 || pathList.isEmpty())) {
            List<SychnoizedDTO> filePathList = new CopyOnWriteArrayList<>();
            Iterator<SychnoizedDTO> iterator = pathList.iterator();
            while (iterator.hasNext()){
                SychnoizedDTO dto = iterator.next();
                String html4 = StringEscapeUtils.unescapeHtml4(dto.getAnnex());
                Pattern pattern = Pattern.compile("<a href=\"(.*?)\".*?>(.*?)</a>");
                Matcher matcher = pattern.matcher(html4);
                if (matcher.find()) {
                    String filePath = matcher.group(1);
                    if (filePath.matches(".*\\\\.*")) {
                        filePath = filePath.replaceAll("\\\\", "/");
                    }

                    SychnoizedDTO sychnoizedDTO = new SychnoizedDTO();
                    sychnoizedDTO.setWorkFlowGUID(dto.getWorkFlowGUID());
                    sychnoizedDTO.setAnnex(filePath);
                    filePathList.add(sychnoizedDTO);
                }
            }

            List<SysAttechment> desPathList = new CopyOnWriteArrayList<>();

            Iterator<SychnoizedDTO> iterator1 = filePathList.iterator();
            while (iterator1.hasNext()){
                SychnoizedDTO dto = iterator1.next();

                String srcfile = DEDA_BASE_PATH + "/" + dto.getAnnex();

                File file = new File(srcfile);
                long size = file.length();
                int index = dto.getAnnex().lastIndexOf("/");
                String s = dto.getAnnex().substring(index + 1);
                String suffex = s.substring(s.lastIndexOf(".") + 1);
                String fileName = s.substring(0,s.lastIndexOf("."));
                StringBuffer rootPath = new StringBuffer();
                rootPath.append(DEDA_HSL_PATH);
                rootPath.append("/Attechment");

                SysAttechment attechment = new SysAttechment();

                StringBuffer destfile = new StringBuffer(rootPath + "/"+ UUID.randomUUID() + "/" + fileName + "." + suffex);
                SynchUtils.downloadAnnex(srcfile, destfile);
                String hslName = destfile.substring(DEDA_HSL_PATH.length());

                attechment.setAttchOwner(dto.getWorkFlowGUID());
                attechment.setAttchName(fileName + "." + suffex);
                attechment.setAttchPath(hslName);
                attechment.setAttchType(suffex);
                attechment.setAttchSize(size);
                attechment.setAttchCreatorID("admin");
                attechment.setAttchNodeID("bill_notice");
                desPathList.add(attechment);
            }

            Iterator<SysAttechment> iterator2 = desPathList.iterator();
            while (iterator2.hasNext()){
                SysAttechment next = iterator2.next();
                materialSychronizeMapper.insertHSLAnnex(next);
            }

            return "数据同步成功,共同步" + row + "条数据";
        }else {
            return "请上传结算通知单";
        }
    }

    /**
     * 同步公新闻数据
     * @return
     */
    @Override
    public String newsSynchronize() {
        List<ContractMiddle> middleList = sysNewsMapper.getMiddleData();

        if (middleList.size() < 1 || middleList.isEmpty()) {
            return null;
        }

        Integer row = sysNewsMapper.startSynchronize(middleList);

        List<News> NewsList = sysNewsMapper.selectByWorkFlowGUID(middleList);

        List<News> NewsLists = new CopyOnWriteArrayList<>();
        NewsList.forEach(news -> {
            String html4 = StringEscapeUtils.unescapeHtml4(news.getContent());
            News news1 = new News();
            news1.setContent(html4);
            news1.setWorkFlowGUID(news.getWorkFlowGUID());
            NewsLists.add(news1);
        });

        NewsLists.forEach(news ->{
            sysNewsMapper.updateContent(news);
        });
        sysNewsMapper.updateSynchronizeStatus(middleList);

        List<SychnoizedDTO> pathList = sysNewsMapper.getPathList(middleList);
        if (!(pathList.size() < 1 || pathList.isEmpty())) {
            List<SychnoizedDTO> filePathList = new CopyOnWriteArrayList<>();
            Iterator<SychnoizedDTO> iterator = pathList.iterator();
            while (iterator.hasNext()){
                SychnoizedDTO dto = iterator.next();
                String html4 = StringEscapeUtils.unescapeHtml4(dto.getAnnex());
                Pattern pattern = Pattern.compile("<a href=\"(.*?)\".*?>(.*?)</a>");
                Matcher matcher = pattern.matcher(html4);
                if (matcher.find()) {
                    String filePath = matcher.group(1);
                    if (filePath.matches(".*\\\\.*")) {
                        filePath = filePath.replaceAll("\\\\", "/");
                    }

                    SychnoizedDTO sychnoizedDTO = new SychnoizedDTO();
                    sychnoizedDTO.setWorkFlowGUID(dto.getWorkFlowGUID());
                    sychnoizedDTO.setAnnex(filePath);
                    filePathList.add(sychnoizedDTO);
                }
            }

            //同步附件
            List<SysAttechment> desPathList = new CopyOnWriteArrayList<>();

            Iterator<SychnoizedDTO> iterator1 = filePathList.iterator();
            while (iterator1.hasNext()){
                SychnoizedDTO dto = iterator1.next();

                String srcfile = DEDA_BASE_PATH + "/" + dto.getAnnex();

                File file = new File(srcfile);
                long size = file.length();
                int index = dto.getAnnex().lastIndexOf("/");
                String s = dto.getAnnex().substring(index + 1);
                //后缀
                String suffex = s.substring(s.lastIndexOf(".") + 1);
                //文件名
                String fileName = s.substring(0,s.lastIndexOf("."));
                StringBuffer rootPath = new StringBuffer();
                rootPath.append(DEDA_HSL_PATH);
                rootPath.append("/Attechment");

                SysAttechment attechment = new SysAttechment();

                StringBuffer destfile = new StringBuffer(rootPath + "/"+ UUID.randomUUID() + "/" + fileName + "." + suffex);
                SynchUtils.downloadAnnex(srcfile, destfile);
                String hslName = destfile.substring(DEDA_HSL_PATH.length());

                attechment.setAttchOwner(dto.getWorkFlowGUID());
                attechment.setAttchName(fileName + "." + suffex);
                attechment.setAttchPath(hslName);
                attechment.setAttchType(suffex);
                attechment.setAttchSize(size);
                attechment.setAttchCreatorID("admin");
                attechment.setAttchNodeID("zal_FilePath");
                desPathList.add(attechment);
            }

            Iterator<SysAttechment> iterator2 = desPathList.iterator();
            while (iterator2.hasNext()){
                SysAttechment next = iterator2.next();
                sysNewsMapper.insertHSLAnnex(next);
            }

            return "数据同步成功,共同步" + row + "条数据";
        }else {
            return "请上传结算通知单";
        }

    }

}
