package com.dedasp.system.service.schedule.impl;

import com.dedasp.common.utils.dedasp.SynchUtils;
import com.dedasp.system.domain.spdomain.ContractMiddle;
import com.dedasp.system.domain.spdomain.SysAttechment;
import com.dedasp.system.dto.SychnoizedDTO;
import com.dedasp.system.mapper.spmapper.schedule.EvaluteBidMapper;
import com.dedasp.system.service.schedule.EvaluateBidService;
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
public class EvaluateBidServiceImpl implements EvaluateBidService {
    @Resource
    private EvaluteBidMapper evaluteBidMapper;

    @Override
    public String evaluteSynchronize() {
        List<ContractMiddle> middleList = evaluteBidMapper.getMiddleData();

        if (middleList.size() < 1 || middleList.isEmpty()) {
            return null;
        }

        Integer row = evaluteBidMapper.startSynchronize(middleList);

        evaluteBidMapper.updateSynchronizeStatus(middleList);

        return "数据同步成功,共同步" + row + "条数据";
    }
}
