package com.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.StockShA;
import com.service.StockShService;
import com.service.impl.StockShServiceImpl;

public class Test {

	@Autowired
	StockShServiceImpl stockService;
	
	/**
	 *	@author LTQ
	 *	@function 
	 *  @param 
	 */
	public static void main(String[] args) {
		ReportUtil ru = new ReportUtil();
		Test t = new Test();
		int page = 166;
		String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page="+page+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6646690248959264";
		//"http://quote.eastmoney.com/center/list.html#33/";
		String source = ru.getHtmlUrl(url, "GBK");
		Document document = ru.findHtml(url, "gbk");	//ISO-8859-1,gbk,utf-8
		//System.out.println(source);
		System.out.println(document);
		//List<HashMap<String,Object>> list = ru.findMessage(url,"utf-8");
		//List<HashMap<String,Object>> list = ru.findMessage(url,null,"gbk");
		List<StockShA> list = ru.findMessage(url,null,"gbk",Integer.toString(page));
		//System.out.println("数据库信息：\n"+t.findStockA());
		System.out.println("获取列表信息：\n"+list);
		/*for (StockA stockA : list) {
			
			
		}*/
		
	}

	public List<StockShA> findStockA(){
		List<StockShA> slist = null;
		try {
			slist = stockService.findStockAAll("1", "12", "12", "code");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slist;
		
	}
	
	public int addStockA(StockShA stockA){
		int i = 0;
		
		stockA.setId(UUID.randomUUID().toString());
		/*
		sA.setCode(hashMap.get("编号").toString());
		sA.setName(hashMap.get("股票名").toString());
		sA.setType(hashMap.get("类型").toString());
		sA.setPrice(Double.parseDouble(hashMap.get("最新价").toString()));
		sA.setPricechange(Double.parseDouble(hashMap.get("涨跌额").toString()));
		sA.setPercent(hashMap.get("涨跌幅").toString());
		sA.setSwing(hashMap.get("振幅").toString());
		sA.setVolume(hashMap.get("成交量(手)").toString());
		sA.setTurnvolume(hashMap.get("成交额(万)").toString());
		sA.setYesterday(Double.parseDouble(hashMap.get("昨收	").toString()));
		sA.setStart(Double.parseDouble(hashMap.get("今开	").toString()));
		sA.setMax(Double.parseDouble(hashMap.get("最高").toString()));
		sA.setMin(Double.parseDouble(hashMap.get("最低").toString()));*/
		
		stockService.addStockA(stockA);
		return i;
	}
}
