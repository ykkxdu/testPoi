package com.example.demo.entity;

import com.deepoove.poi.data.NumbericRenderData;
import lombok.Data;

/**
 * Created by Administrator on 2018/8/15.
 */
@Data
public class ExperienceData {
    private String company;
    private String department;
    private String time;
    private String position;
    private NumbericRenderData responsibility;
}
