package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pojo.StockShA;



/**
 * @author LTQ
 * @package com.ltq.util
 * @function 数据抓取核心类：使用原理和jsoup包
 */
public class ReportUtil {
	/**
	 *	@author LTQ
	 *  @function 根据网址获取源代码:使用原理，利用io流储存url信息
	 *	@param url 网址
	 *	@param encoding 编码
	 *	@return 以字符串返回web页面,不自动排版
	 */
	ReportUtil reportUtil;
	private Log log = LogFactory.getLog(ReportUtil.class);
	
	public String getHtmlUrl(String url,String encoding){
		
		StringBuffer buffer1 = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL urlObject = new URL(url);					//获取连接
			URLConnection uc = urlObject.openConnection();	//打开连接
			//请求HTTP协议的头信息里面的属性，比如Cookie、User-Agent（浏览器类型）等等
			uc.setRequestProperty("user-Agent", "java");	//跳过防火墙
			//数据筛选
			isr = new InputStreamReader(uc.getInputStream());//建立文件输入流
			br = new BufferedReader(isr);					//输入字符流
			String temp = null;								//临时文件
			while((temp = br.readLine())!= null){
				buffer1.append(temp+"\n");
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (isr != null) {
				try {
					isr.close();				//关闭流节省资源
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return buffer1.toString();
	}
	/**
	 *	@author LTQ
	 *	@function 使用第一种方法，根据网址获取源代码:利用jsoup封装，效果比原始的io流更好，手动强制确定编码
	 *  @param url 网址
	 *  @return 以字符串返回web页面,自动排版
	 */
	public Document findHtml(String url,String encoding){
		//拿到源代码
		String htmlSource = getHtmlUrl(url, encoding);
		//解析源代码
		log.debug("使用第一种方法，解析源代码。。。");
		Document document = Jsoup.parse(htmlSource);
		return document;
	}
	
	/**
	 *	@author LTQ
	 *	@function 使用第二种方法，系统识别编码，直接获取网页并解析（不强制确定编码，复合网页容易编码错误）
	 *  @param
	 */
	public Document findHtml(String url){
		Document document = null;
		//从一个网站获取和解析一个HTML文档，并查找其中的相关数据
		log.debug("使用第二种方法，直接获取网页并解析。。。");
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 *	@author LTQ
	 *	@function 根据源代码解析获取数据
	 *  @return 从指定url中抽取信息后的统计结果，格式为List-HashMap集合
	 */
	public List<HashMap<String,Object>> findMessage(String url,String encoding){
		List<HashMap<String,Object>> datas = new ArrayList<HashMap<String,Object>>();
		Document document = findHtml(url,encoding);
		//获取类名为对应值的标签，取第一个区域内容
		Elements elements = document.getElementsByClass("head_wrapper");	//针对不同的网页需要改标签里的class名，缺乏通用性
		System.out.println("\nelements:"+elements.size());	//元素
		Element element;
		//如果元素不为空拿到所有的a标签
		if(elements.size()>0){
			element = elements.get(0);
			elements = element.getElementsByTag("a");
		}
		//遍历所有的a拿到值
		int key = 0;
		for(Element e:elements){
			HashMap<String, Object> map = new HashMap<String, Object>();
			String value = e.text();		//获取文本部分
			String href = e.attr("href");	//获取标签内属性
			map.put("key", key);
			map.put("value",value);
			map.put("href", href);
			if(e.text().indexOf("(")>0){
				String[] text = e.text().split("(");	//利用分割等方法可以对数据进一步提取
				String ct1 = text[0];
				String num = text[1].replaceAll(")", "");
				System.out.println("text.length:"+text.length);
				map.put("ct1",ct1);
				map.put("num",num);
			}
			datas.add(map);
			key++;
		}
		
		
		return datas;
	}
	
	/**
	 *	@author LTQ
	 *	@function 得到目标详细信息
	 *	@param url 网址，可添加参数
	 *  @param name 类名或id名,null则为空
	 *  @param encoding 当前不可用，填任何值等于没填
	 *  @param page 当前页，可为空（不填）
	 */
	public List<StockShA> findMessage(String url,String name,String encoding,String page){
		List<StockShA> slist = new LinkedList<StockShA>();
		//List<HashMap<String,Object>> datas = new ArrayList<HashMap<String,Object>>();
		Document document = findHtml(url);	//findHtml(url,encoding)
		//获取类名为对应值的标签，取第一个区域内容
		Element element = null;
		Elements elements = null;
		if(name != null){
			element = document.getElementById(name);	//针对不同的网页需要改标签里的id名
			elements = element.getAllElements();
		}else{
			elements = document.getElementsByTag("body");
		}
		System.out.println("elements:\n"+elements);	//元素
		int start = elements.text().indexOf("{");
		String data = elements.text().substring(start);
		System.out.println("json="+data);
		String subdata = data.substring(8,data.length()-13);
		String [] array = subdata.split("\",\"");
		
		System.out.println(subdata);
		for (int i = 0; i < array.length; i++) {
			String [] array2 = array[i].split(",");
			StockShA stockA = new StockShA();
			stockA.setStockA(array2,page);
			/*HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("类型", array2[0]);
			map.put("编号", array2[1]);
			map.put("股票名", array2[2]);
			map.put("最新价", array2[3]);
			map.put("涨跌额", array2[4]);
			map.put("涨跌幅", array2[5]);
			map.put("振幅", array2[6]);
			map.put("成交量(手)", array2[7]);
			map.put("成交额(万)", array2[8]);
			map.put("昨收	", array2[9]);
			map.put("今开	", array2[10]);
			map.put("最高", array2[11]);
			map.put("最低", array2[12]);
			System.out.println("左手="+map);
			datas.add(map);*/
			slist.add(stockA);
		}
		
		return slist;
	}
	
	
}
