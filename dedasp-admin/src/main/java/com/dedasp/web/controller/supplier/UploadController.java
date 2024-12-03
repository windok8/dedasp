package com.dedasp.web.controller.supplier;

import cn.hutool.core.io.FileUtil;
import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.annotation.Log;
import com.dedasp.common.core.controller.BaseController;
import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.common.enums.BusinessType;
import com.dedasp.common.utils.dedasp.UploadUtils;
import com.dedasp.system.domain.spdomain.SysAnnex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.dedasp.common.constant.SystemConstants.FILE_PATH;

@RestController
@RequestMapping("/supplier")
public class UploadController extends BaseController {

    @Anonymous
    @PostMapping("/upload")
    public AjaxResult fileUpload(@RequestParam("files") MultipartFile[] files){
        List<String> timeList = UploadUtils.initTime();
        StringBuffer rootPath = new StringBuffer();
        rootPath.append(FILE_PATH);
        timeList.forEach(time ->{
            rootPath.append("\\");
            rootPath.append(time);
        });

        List<SysAnnex> annexes = new ArrayList<>();

        for (MultipartFile file : files){
            if (file.isEmpty()){
                annexes.add(null);
                continue;
            }

            String originalFilename = file.getOriginalFilename();
            String mainName = FileUtil.mainName(originalFilename);
            String extName = FileUtil.extName(originalFilename);
            if (!FileUtil.exist(rootPath.toString())) {
                FileUtil.mkdir(rootPath.toString());
            }
            if (FileUtil.exist(rootPath + File.separator + originalFilename)){
                mainName = System.currentTimeMillis() + "_" + mainName;
                originalFilename = mainName + "." + extName;
            }

            File saveFile = new File(rootPath + File.separator + originalFilename);
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String finalFilePath = rootPath.substring(26);
            annexes.add(new SysAnnex(finalFilePath,mainName,extName));
        }

        return AjaxResult.success(annexes);

    }


}
