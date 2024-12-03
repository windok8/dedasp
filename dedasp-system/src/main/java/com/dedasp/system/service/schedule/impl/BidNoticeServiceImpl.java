package com.dedasp.system.service.schedule.impl;

import com.dedasp.common.utils.dedasp.SynchUtils;
import com.dedasp.system.domain.spdomain.ContractMiddle;
import com.dedasp.system.domain.spdomain.SysAttechment;
import com.dedasp.system.dto.SunshineDTO;
import com.dedasp.system.dto.SychnoizedDTO;
import com.dedasp.system.mapper.spmapper.schedule.BidNoticeMapper;
import com.dedasp.system.service.schedule.BidNoticeService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dedasp.common.constant.SystemConstants.DEDA_BASE_PATH;
import static com.dedasp.common.constant.SystemConstants.DEDA_HSL_PATH;

@Service
public class BidNoticeServiceImpl implements BidNoticeService {
    @Resource
    private BidNoticeMapper bidNoticeMapper;

    /**
     * 同步中标公告
     */
    @Override
    public String bidSynchronize() {
        List<ContractMiddle> middleList = bidNoticeMapper.getMiddleData();

        if (middleList.size() < 1 || middleList.isEmpty()) {
            return null;
        }

        Integer row = bidNoticeMapper.startSynchronize(middleList);

        bidNoticeMapper.updateSynchronizeStatus(middleList);

        List<SysAttechment> desPathList = new CopyOnWriteArrayList<>();

        List<SychnoizedDTO> pathList = bidNoticeMapper.getPathList(middleList);
        if (!(pathList.size() < 1 || pathList.isEmpty())) {
            Iterator<SychnoizedDTO> iterator = pathList.iterator();
            while (iterator.hasNext()){
                SychnoizedDTO sychnoizedDTO = iterator.next();
                String html4 = StringEscapeUtils.unescapeHtml4(sychnoizedDTO.getAnnex());
                Pattern pattern = Pattern.compile("<a[^>]*href=['\"](.*?)['\"]");
                Matcher matcher = pattern.matcher(html4);
                while (matcher.find()){
                    String filePath = matcher.group(1);
                    if (filePath.matches(".*\\\\.*")) {
                        filePath = filePath.replaceAll("\\\\", "/");
                    }

                    String srcfile = DEDA_BASE_PATH + "/" + filePath;

                    File file = new File(srcfile);
                    long size = file.length();
                    int index = filePath.lastIndexOf("/");
                    String s = filePath.substring(index + 1);
                    String suffex = s.substring(s.lastIndexOf(".") + 1);
                    String fileName = s.substring(0,s.lastIndexOf("."));
                    StringBuffer rootPath = new StringBuffer();
                    rootPath.append(DEDA_HSL_PATH);
                    rootPath.append("/Attechment");

                    SysAttechment attechment = new SysAttechment();

                    StringBuffer destfile = new StringBuffer(rootPath + "/"+ UUID.randomUUID() + "/" + fileName + "." + suffex);
                    SynchUtils.downloadAnnex(srcfile, destfile);
                    String hslName = destfile.substring(DEDA_HSL_PATH.length());

                    attechment.setAttchOwner(sychnoizedDTO.getWorkFlowGUID());
                    attechment.setAttchName(fileName + "." + suffex);
                    attechment.setAttchPath(hslName);
                    attechment.setAttchType(suffex);
                    attechment.setAttchSize(size);
                    attechment.setAttchCreatorID("admin");
                    attechment.setAttchNodeID("GongShiWenjian");
                    desPathList.add(attechment);
                }
            }
            if (desPathList.size() > 0){
                bidNoticeMapper.insertHSLAnnex(desPathList);
            }

            return "数据同步成功,共同步" + row + "条数据";
        }else {
            return "请上传结算通知单";
        }
    }
}
