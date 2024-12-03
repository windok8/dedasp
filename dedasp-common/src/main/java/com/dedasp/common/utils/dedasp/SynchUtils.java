package com.dedasp.common.utils.dedasp;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dedasp.common.constant.SystemConstants.DEDA_BASE_PATH;
import static com.dedasp.common.constant.SystemConstants.DEDA_HSL_PATH;

public class SynchUtils {
    private static final Logger log = LoggerFactory.getLogger(SynchUtils.class);

    /**
     * 文件拷贝方法
     * @param srcfile 源地址
     * @param desfile 目标地址
     * @return 目标地址集合
     */
    public static void downloadAnnex(String srcfile, StringBuffer desfile){
        File file = new File(desfile.toString());
        String desPath = file.getParent();
        desPath = desPath.replaceAll("\\\\","/");

        while (FileUtil.exist(desPath)){
            desPath = desPath.substring(0,desPath.lastIndexOf("/"));
            desfile = new StringBuffer(desPath);
            desfile.append("/");
            desfile.append(UUID.randomUUID());
            desPath = desfile.toString();
            desfile.append("/");
            desfile.append(file.getName());
        }

        FileUtil.mkdir(desPath);

        FileInputStream in = null;

        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcfile);
            out = new FileOutputStream(desfile.toString());
            byte[] bytes = new byte[1024 * 1024];
            int readCount = 0;
            while ((readCount = in.read(bytes)) != -1){
                out.write(bytes,0,readCount);
            }

            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 多个附件,只取一个
     * @param imagePath
     * @return
     */
    public static String formatImagePath(String imagePath){
        String regex = "&lt;div&gt;(.*?)&lt;\\/div&gt;";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(imagePath);

        if (matcher.find()) {
           imagePath = matcher.group(1);
        }

        String html4 = StringEscapeUtils.unescapeHtml4(imagePath);
        Pattern pattern2 = Pattern.compile("<a href=\"(.*?)\".*?>(.*?)</a>");
        Matcher matcher2 = pattern2.matcher(html4);
        if (matcher2.find()) {
            imagePath = matcher2.group(1);
        }


        return imagePath;
    }

    public static String getOriginDedaPath(String annex){
        return null;
    }

    public static String getDestDedaPath(String fileName){
        StringBuffer destFile = new StringBuffer(DEDA_BASE_PATH);
        destFile.append("/UserUploadFiles");
        List<String> timeList = UploadUtils.initTime();
        timeList.forEach(time -> {
            destFile.append("/");
            destFile.append(time);
        });
        destFile.append("/");
        destFile.append(UUID.randomUUID());
        destFile.append("/");
        destFile.append(fileName);
        return destFile.toString();
    }

    public static String getOriginHSLPath(String annex,String id,boolean flag){
        int index = annex.lastIndexOf("/");

        String fileName = "";

        if (flag){
            fileName = "[已签]" + id + "_" + annex.substring(index + 1);
            annex = "/SealFiles/" + fileName;
        }else {
            fileName = annex.substring(index + 1);
            annex = annex.substring(0, index + 1) + fileName;
        }
        String srcFile = DEDA_HSL_PATH + annex;
        return srcFile;
    }

    public static String getDestHSLPath(String fileName){
        return null;
    }


}
