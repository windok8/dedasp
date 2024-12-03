package com.dedasp.common.constant;

import cn.hutool.core.io.FileUtil;

public class SystemConstants {
    //文件上传第三方地址
    public static final String FILE_PATH = "D:\\DZGL\\SystemFrameWorkV3\\UserUploadFiles";

    //程序根目录
    public static final String DOWNLOAD_MIDDLE_PATH = FileUtil.getWebRoot().getPath() + "\\src\\main\\resources\\static\\download";

    //德达文件基路径
    public static final String DEDA_BASE_PATH = "D:/DZGL/SystemFrameWorkV3";

    //红树林文件基路径
    public static final String DEDA_HSL_PATH = "D:/DZGL/Suplier";
}
