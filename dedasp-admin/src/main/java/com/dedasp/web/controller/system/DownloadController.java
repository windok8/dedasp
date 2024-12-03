package com.dedasp.web.controller.system;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/tender/open")
public class DownloadController {


    @PostMapping("/downloadFile")
    public void downloadFile(@RequestBody String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException {

        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        String filePathFiexd = "D:\\DZGL\\SystemFrameWorkV3\\";
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
}
