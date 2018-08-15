package com.example.demo.model;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/13.
 */
public class TestTable {
    public void tableData(Map<String,Object> map)
    {
        RowRenderData header=RowRenderData.build(new TextRenderData("FFFFFF","姓名"),new TextRenderData("FFFFFF","学历"));
        RowRenderData row0=RowRenderData.build("张三","研究生");
        RowRenderData row1=RowRenderData.build("李四","博士");
        RowRenderData row2=RowRenderData.build("王五","博士后");
        map.put("table",new MiniTableRenderData(header, Arrays.asList(row0,row1,row2)));
    }

}
