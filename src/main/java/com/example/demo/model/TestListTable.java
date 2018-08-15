package com.example.demo.model;

import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/14.
 */
public class TestListTable {
    public void listTableData(Map<String,Object> map)
    {
        map.put("feature",new NumbericRenderData(new ArrayList<TextRenderData>()
        {{
            add(new TextRenderData("Plug-in grammer"));
            add(new TextRenderData("Support word test header...."));
            add(new TextRenderData("Not just templates,but also style template"));
        }
        }));
    }
}
