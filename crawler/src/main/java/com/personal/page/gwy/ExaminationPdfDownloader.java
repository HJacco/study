package com.personal.page.gwy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
@AllArgsConstructor
public class ExaminationPdfDownloader {
    private String fileName;
    private static final String BASE_DIR = "/Users/huangchongjie/Downloads/zl/%s/%s/";
    private String downloadUrl;

    public void download() throws Exception {
        int idx_0 = fileName.indexOf("年") + 1;
        String provence = fileName.substring(idx_0, idx_0 + 2);
        String dir = "";
        if (fileName.indexOf("行测") > -1) {
            dir = String.format(BASE_DIR, provence, "行测");
        } else {
            dir = String.format(BASE_DIR, provence, "申论");
        }
        if (Files.notExists(Paths.get(dir))) {
            Files.createDirectories(Paths.get(dir));
        }
        download(downloadUrl, dir, fileName + ".pdf");
    }

    @Override
    public String toString() {
        return "ExaminationPdfDownloader{" +
                "fileName='" + fileName + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }

    public void download(String urlPath , String targetDirectory, String name) throws Exception {
        // 解决url中可能有中文情况
        URL url = new URL(urlPath);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setConnectTimeout(3000);
        // 设置 User-Agent 避免被拦截
        http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
        String contentType = http.getContentType();
//        System.out.println("contentType: "+ contentType);
        // 获取文件大小
        long length = http.getContentLengthLong();
//        System.out.println("文件大小："+(length / 1024)+"KB");

        String newName = name;
        InputStream inputStream = http.getInputStream();
        byte[] buff = new byte[1024*10];
        File file = new File(targetDirectory,newName);
        OutputStream out = new FileOutputStream(file);
        int len ;
        int count = 0; // 计数
        while((len = inputStream.read(buff)) != -1) {
            out.write(buff, 0, len);
            out.flush();
            ++count ;
        }
        System.out.println("count:"+ count);
        // 关闭资源
        out.close();
        inputStream.close();
        http.disconnect();
    }
}
