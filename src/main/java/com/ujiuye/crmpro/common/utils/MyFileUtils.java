package com.ujiuye.crmpro.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyFileUtils {

    public static List<File> upload(MultipartFile[] multipartFiles,String path){
        if(path==null){
            path="/Users/ClinEvol/Workspaces/static/";
        }
        List<File> fileList=new ArrayList<>();
        //判断数组中有没有文件
        if(multipartFiles.length>0){
            //遍历
            for(MultipartFile multipartFile:multipartFiles){
                //判断文件的大小
                if(multipartFile.getSize()>0){
                    //获取文件名
                    String filename = multipartFile.getOriginalFilename();
                    //-------------重合名文件----------------
                    //获攻取后缀名,不包括.
                    String suffixName=filename.substring(filename.lastIndexOf(".")+1);
                    //filejpg201910171528.jpg
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
                    String time=simpleDateFormat.format(new Date());
                    //随数
                    String radom=String.valueOf(Math.random()).substring(2,6);
                    //拼接文件名
                    String newFileName="file"+suffixName+time+radom+"."+suffixName;

                    String realPath=path+newFileName;
                    //创建File对象   这个对象是空的
                    File file=new File(realPath);
                    try {
                        //使用工具类实现写出内容
                        multipartFile.transferTo(file);
                        fileList.add(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            System.out.println("没有选择文件");
        }
        return fileList;
    }



    public static ResponseEntity<byte[]> download(String filename,String path) {
        if(path==null){
            path="/Users/ClinEvol/Workspaces/static/";
        }
        try {
            //文件名乱码问题，用于下载时显示
            String newName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
            //file为被下载的文件对象，有内容
            File file=new File(path+filename);
            //文件头
            HttpHeaders header=new HttpHeaders();
            //设置弹框
            header.setContentDispositionFormData("attchment",newName);
            // MIME:类型
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            ResponseEntity<byte[]> responseEntity=new ResponseEntity<>(FileUtils.readFileToByteArray(file),header, HttpStatus.OK);
            return responseEntity;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("编码转换异常");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


}
