package com.example.demo.entity;

import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/8/14.
 */
@Data
public class DetailData {
    // 货品数据
    private List<RowRenderData> goods;

    // 人工费数据
    private List<RowRenderData> labors;
}
