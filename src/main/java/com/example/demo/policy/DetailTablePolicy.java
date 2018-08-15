package com.example.demo.policy;
import com.deepoove.poi.NiceXWPFDocument;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.example.demo.entity.DetailData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import java.util.List;
/**
 * Created by Administrator on 2018/8/14.
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {
    // 货品填充数据所在的行数,下标从0开始。
    int goodsStartRow=2;
    // 人工费填充数据所在行数
    int laborsStartRow=5;
    @Override
    public void render(XWPFTable xwpfTable, Object o) {
        if(o==null) return;
        DetailData detailData= (DetailData) o;
        List<RowRenderData> labors=detailData.getLabors();
        if (labors!=null)
        {
            xwpfTable.removeRow(laborsStartRow);
            // 循环的插入行
            for(int i=0;i<labors.size();i++)
            {
                XWPFTableRow row=xwpfTable.insertNewTableRow(laborsStartRow);
                for(int j=0;j<7;j++)
                {
                    row.createCell();
                }
                // 合并单元格
                NiceXWPFDocument.mergeCellsHorizonal(xwpfTable,laborsStartRow,0,3);
                // 渲染单行人工费数据
                MiniTableRenderPolicy.renderRow(xwpfTable,laborsStartRow,labors.get(i));
                      }
        }
        List<RowRenderData> goods=detailData.getGoods();
        if(goods!=null)
        {
            xwpfTable.removeRow(goodsStartRow);
            for(int i=0;i<goods.size();i++)
            {
                XWPFTableRow xwpfTableRow=xwpfTable.insertNewTableRow(goodsStartRow);
                for(int j=0;j<7;j++) xwpfTableRow.createCell();
                MiniTableRenderPolicy.renderRow(xwpfTable,goodsStartRow,goods.get(i));
            }
        }


    }
}
