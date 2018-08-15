package com.example.demo.entity;

import lombok.Data;

/**
 * Created by Administrator on 2018/8/15.
 */
@Data
public class UpLoadFile {
    // 文件名
    private String filename;
    // 文件类型
    private Boolean isDirectory;
    // 文件大小(bytes)
    private Long size;

    public UpLoadFile() {
    }
}
