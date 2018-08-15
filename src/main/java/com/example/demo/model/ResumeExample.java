package com.example.demo.model;

import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.example.demo.entity.ExperienceData;
import com.example.demo.entity.ResumeData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/8/15.
 */
public class ResumeExample {
    public void initResumeData(ResumeData datas)
    {
        datas.setPortrait(new PictureRenderData(100, 100, "report/upload/sun.jpg"));
        datas.setName("严凯凯");
        datas.setJob("Java开发工程师");
        datas.setPhone("15229069152");
        datas.setSex("男");
        datas.setProvince("陕西");
        datas.setBirthday("1994.06.29");
        datas.setEmail("1045110506@qq.com");
        datas.setAddress("陕西省西安市西安电子科技大学");
        datas.setEnglish("CET6 620");
        datas.setUniversity("西安电子科技大学");
        datas.setProfession("软件工程");
        datas.setEducation("硕士");
        datas.setRank("班级排名 30/60");
        datas.setHobbies("音乐、画画、乒乓球、旅游、读书\nhttps://github.com/Sayi");
        // 技术栈部分
        TextRenderData textRenderData = new TextRenderData("SpringBoot、SprigCloud、Mybatis");
        Style style = new Style();
        style.setFontSize(10);
        style.setColor("7F7F7F");
        style.setFontFamily("微软雅黑");
        textRenderData.setStyle(style);
        datas.setNumbericRenderData(new NumbericRenderData(
                Arrays.asList(textRenderData, textRenderData, textRenderData)));

        // 模板文档循环合并
        List<ExperienceData> experiences = new ArrayList<ExperienceData>();
        ExperienceData data0 = new ExperienceData();
        data0.setCompany("香港微软");
        data0.setDepartment("Office 事业部");
        data0.setTime("2001.07-2020.06");
        data0.setPosition("BUG工程师");
        textRenderData = new TextRenderData("负责生产BUG，然后修复BUG，同时有效实施招聘行为");
        textRenderData.setStyle(style);
        data0.setResponsibility(new NumbericRenderData(
                Arrays.asList(textRenderData, textRenderData)));
        ExperienceData data1 = new ExperienceData();
        data1.setCompany("自由职业");
        data1.setDepartment("OpenSource 项目组");
        data1.setTime("2015.07-2020.06");
        data1.setPosition("研发工程师");
        textRenderData = new TextRenderData("开源项目的迭代和维护工作");
        textRenderData.setStyle(style);
        TextRenderData textRenderData1 = new TextRenderData("持续集成、Swagger文档等工具调研");
        textRenderData1.setStyle(style);
        data1.setResponsibility(new NumbericRenderData(
                Arrays.asList(textRenderData, textRenderData1, textRenderData)));
        experiences.add(data0);
        experiences.add(data1);
        experiences.add(data0);
        experiences.add(data1);
        datas.setExperience(new DocxRenderData(new File("report/template/segment.docx"), experiences));
    }
}
