package com.example.demo.data;

import com.example.demo.model.TestListTable;
import com.example.demo.model.TestPicture;
import com.example.demo.model.TestTable;
import com.example.demo.model.TestText;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/15.
 */
public class TestDataFill {
    // 填充各test数据
    public void fillData(HashMap<String,Object> map)
    {
        new TestText().textData(map);
		new TestTable().tableData(map);
		new TestPicture().pictureData(map);
		new TestListTable().listTableData(map);
    }
}
