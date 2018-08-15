package com.example.demo.entity;

import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.PictureRenderData;
import lombok.Data;

/**
 * Created by Administrator on 2018/8/15.
 */
@Data
public class ResumeData {
    private PictureRenderData portrait;
    private String name;
    private String job;
    private String sex;
    private String phone;
    private String birthday;
    private String province;
    private String email;
    private String address;
    private String english;
    private String University;
    private String rank;
    private String education;
    private String profession;
    private NumbericRenderData numbericRenderData;
    private String hobbies;
    private DocxRenderData experience;
}
