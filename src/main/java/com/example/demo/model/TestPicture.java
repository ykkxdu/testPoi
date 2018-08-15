package com.example.demo.model;

import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/14.
 */
public class TestPicture {
    public void pictureData(Map<String,Object> map)
    {
        // 从网络上加载图片
        map.put("urlPicture",new PictureRenderData(100, 100, ".png",
                BytePictureUtils.getUrlByteArray("https://avatars3.githubusercontent.com/u/1394854")));
        // 本地图片
        Path path= Paths.get("report","upload","sun.jpg");
        byte[] localByteArray = BytePictureUtils.getLocalByteArray(new File(path.toString()));
        map.put("nativePicture",new PictureRenderData(200,200,".jpg",localByteArray));
    }
}
