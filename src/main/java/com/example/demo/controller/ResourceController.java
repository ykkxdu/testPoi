package com.example.demo.controller;

import com.example.demo.entity.UpLoadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.FileUtils.REPORT_DIR;
import static com.example.demo.model.FileUtils.isExist;

/**
 * 资源管理接口
 * 上传和下载文件接口
 */
@Controller
@RequestMapping("api/resource")
@ResponseBody
public class ResourceController {
    @Autowired
    private ResourceLoader resourceLoader;
    /**
     * 上传文件接口
     *
     * @param uploadFiles 上传文件列表
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile[] uploadFiles)
    {
        if(uploadFiles==null||uploadFiles.length==0)
        {
            return ResponseEntity.badRequest().build();
        }
        try {
            ArrayList<String> fileNames = new ArrayList(uploadFiles.length);
            for (MultipartFile uploadFile : uploadFiles) {
                String originalFilename = uploadFile.getOriginalFilename();
                Long timestamp = System.currentTimeMillis(); // 时间戳
                String timestampStr = String.valueOf(timestamp);
                int lastIndexOfDot = originalFilename.lastIndexOf(".");
                String prefix = originalFilename; // 前缀
                String suffix = ""; // 后缀
                if (lastIndexOfDot > -1) {
                    prefix = originalFilename.substring(0, lastIndexOfDot);
                    suffix = originalFilename.substring(lastIndexOfDot);
                }
                // 给文件名扩展类型.前加时间戳
                String fileName = prefix + "_" + timestampStr + suffix;
                Files.copy(uploadFile.getInputStream(), Paths.get(REPORT_DIR,fileName));
                fileNames.add(fileName);
        }
        return ResponseEntity.ok(fileNames);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
    /**
     * 下载文件接口
     *
     * @param fileName 要下载的文件名
     * @return
     */
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity download(@PathVariable("fileName")String fileName)
    {
         try {
             // 通过文件名获取文件路径
             Path path=Paths.get(REPORT_DIR,fileName);
             String fullFileName=path.toString();
             if (isExist(fullFileName))
             {
                 Resource resource=resourceLoader.getResource("file:"+fullFileName);
                 HttpHeaders headers=new HttpHeaders();
                 headers.add("Cache-Control","no-cache, no-store, must-revalidate");
                 headers.add("Pragma","no-cache");
                 headers.add("Expires", "0");
                 headers.add(
                           "Content-Disposition",
                          "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8")
                 );
                 return ResponseEntity
                         .ok()
                         .headers(headers)
                         .contentLength(resource.contentLength())
                         .body(resource);
             }else{
                 return ResponseEntity.notFound().build();
             }
         }catch (Exception e)
         {
             e.printStackTrace();
             return ResponseEntity.notFound().build();
         }
    }
    /**
     * 列出上传目录下的文件列表
     *
     * @param path 要查询的目录（根目录为 root，即为 /upload）
     * @return
     */
    @GetMapping("/list/{path:.*}")
    public ResponseEntity listFiles(@PathVariable(name="path")String path)
    {
        try {
            File dir=Paths.get(path).toFile();
            File[] files=dir.listFiles();
            List<UpLoadFile> upLoadFiles=new ArrayList<>(files.length);
            for(File file:files)
            {
                UpLoadFile uploadFile=new UpLoadFile();
                uploadFile.setFilename(file.getName());
                uploadFile.setIsDirectory(file.isDirectory());
                uploadFile.setSize(file.length());
                upLoadFiles.add(uploadFile);
            }
            return ResponseEntity.ok(upLoadFiles);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }


    }

    /**
     * 删除文件接口
     *
     * @param fileName 要删除的文件名
     * @return
     */
    @GetMapping("/delete/{fileName:.+}")
    public ResponseEntity deleteFile(@PathVariable("fileName") String fileName)
    {
        try
        {
           File dir= Paths.get(REPORT_DIR).toFile();
           File[] files = dir.listFiles();
           for (File file : files) {
               if(file.getName().equals(fileName))
               {
                   file.delete();
               }
            }
            return ResponseEntity.ok(fileName);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }

}
