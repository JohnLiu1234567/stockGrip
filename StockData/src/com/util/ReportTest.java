package com.util;

import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.pojo.StockShA;





public class ReportTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		ReportUtil ru = new ReportUtil();
		String url = "http://quote.eastmoney.com/center/list.html#33/";
		String source = ru.getHtmlUrl(url, "GBK");
		Document document = ru.findHtml(url, "gbk");	//ISO-8859-1,gbk,utf-8
		//System.out.println(source);
		System.out.println(document);
		//List<HashMap<String,Object>> list = ru.findMessage(url,"utf-8");
		List<StockShA> list = ru.findMessage(url,"zhrool","gbk",null);
		System.out.println("获取列表信息：\n"+list);
	}

}
