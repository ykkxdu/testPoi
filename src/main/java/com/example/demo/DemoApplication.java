package com.example.demo;

import com.deepoove.poi.XWPFTemplate;
import com.example.demo.data.TemplateData;
import com.example.demo.data.TestDataFill;
import com.example.demo.entity.PaymentData;
import com.example.demo.entity.ResumeData;
import com.example.demo.model.ResumeExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {
	/**
	*	poi-tl学习参考网址:http://deepoove.com/poi-tl/
	 *
	 * 1.TextRenderData 文本模板或者String数据模型,文本换行使用 \n 字符。{{var}}
	 * 2.PictureRenderData 图片模板 {{@var}}
	 * 3.MiniTableRenderData 表格模板 {{#var}}
	 * 4.NumbericRenderData 列表模板 {{*var}}
	 * 5.DocxRenderData 可以是另一个docx文档的合并，或者是数据集针对同一个模板的不同渲染结果的合并。 {{+var}}
	 *
	* */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		FileOutputStream out=null;
		PaymentData paymentData = new PaymentData();
		ResumeData resumeData=new ResumeData();
		String tplOutputName="out_template.docx";
		Path templatePath= Paths.get("report","template","resume.docx");
		Path tplOutPutPath=Paths.get("report",tplOutputName);
		HashMap<String,Object> map=new HashMap<>();
		new PaymentData().initData(paymentData);
		new ResumeExample().initResumeData(resumeData);
		map=new TemplateData<PaymentData>().fill(map,paymentData);
		map.put("detail_table",paymentData.getDetailTable());
		new TestDataFill().fillData(map);
		// 自定义策略处理表格方法,payment,将数据装载到map中进行渲染。
		/*Configure configure=Configure.newBuilder().customPolicy("detail_table",new DetailTablePolicy()).build();
		XWPFTemplate template=XWPFTemplate.compile(templatePath.toString(),configure);
		template.render(map);
		*/
		// resume，直接渲染实体对象。
		XWPFTemplate template=XWPFTemplate.compile(templatePath.toString()).render(resumeData);
		try {
			out= new FileOutputStream(tplOutPutPath.toString());
			template.write(out);
			out.flush();
			out.close();
			template.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
