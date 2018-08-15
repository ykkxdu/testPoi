package com.example.demo.entity;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/8/14.
 */
@Data
public class PaymentData {
    private MiniTableRenderData order;
    private String NO;
    private String ID;
    private String taitou;
    private String consignee;
    private DetailData detailTable;
    private String subtotal;
    private String tax;
    private String transform;
    private String other;
    private String unpay;
    private String total;
    public void initData(PaymentData datas)
    {
        TableStyle rowStyle = new TableStyle();
        datas.setNO("KB.6890451");
        datas.setID("ZHANG_SAN_091");
        datas.setTaitou("深圳XX家装有限公司");
        datas.setConsignee("丙丁");
        datas.setSubtotal("8000");
        datas.setTax("600");
        datas.setTransform("120");
        datas.setOther("250");
        datas.setUnpay("6600");
        datas.setTotal("总共：7200");
        DetailData detailTable = new DetailData();
        RowRenderData good = RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600");
        good.setStyle(rowStyle);
        List<RowRenderData> goods = Arrays.asList(good, good, good);
        RowRenderData  labor = RowRenderData.build("油漆工", "2", "200", "400");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor, labor, labor, labor);
        detailTable.setGoods(goods);
        detailTable.setLabors(labors);
        datas.setDetailTable(detailTable);
    }
}
