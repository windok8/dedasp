package com.dedasp.web.controller.supplier;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dedasp.common.annotation.Anonymous;
import com.dedasp.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

import static com.dedasp.common.constant.SystemConstants.DEDA_HSL_PATH;

@RestController
@RequestMapping("/supplier/information")
public class InformationDownloadController {
    @Value("${file.download.base.path}")
    String uploadFilePath;

    @Anonymous
    @PostMapping("/downloadFile")
    public void downloadFile(@RequestBody String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException {

        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        String filePathFiexd = DEDA_HSL_PATH;
        String fullFilePath = filePathFiexd + filePath;
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = (new File(fullFilePath)).length();
        response.setContentType("application/octet-stream;charset=GBK");
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));
        bis = new BufferedInputStream(new FileInputStream(fullFilePath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];

        int bytesRead;
        while (-1!= (bytesRead = bis.read(buff, 0, buff.length))){
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    @Anonymous
    @GetMapping("/fileDownload")
    public String fileDownload(@RequestParam String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject result = new JSONObject();
        File file = new File(uploadFilePath + filePath);
        if (!file.exists()){
            result.put("error","下载文件不存在");
            return result.toString();
        }

        response.setContentType("application/octet-stream;charset=GBK");
        response.setHeader("Content-disposition", "attachment; filename=" + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(file.length()));

        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
        result.put("success","下载成功");
        return result.toString();

    }
}
