package com.controller.backstage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.ForbiddenException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.controller.BaseController;
import com.pojo.PriceFrequence;
import com.pojo.StockShA;
import com.pojo.StockShA2;
import com.service.StockSh2Service;
import com.service.StockShService;
import com.util.PageUtil;
import com.util.ReportUtil;

@Controller
@RequestMapping("/backstage/stock")
public class StockController extends BaseController{

	@Autowired
	private StockShService stockShService;
	@Autowired
	private StockSh2Service stockSh2Service;
	
	private Log log = LogFactory.getLog(StockController.class);
	
	@RequestMapping("getstockshaall.action")
	@ResponseBody
	public String getStockShaAll(String name, String type,
			String exchange, String state, String way){
		System.out.println("********getstockshaall********");
		List<StockShA> slist = stockShService.findStockAAll(type, exchange, state, way);
		String json = JSON.toJSONString(slist);
		return addJson("成功", "1000", json);
	}
	
	/**
	 *	@author LTQ
	 *	@function 显示所有股票的所有信息
	 *  @param
	 */
	@RequestMapping("getstocksha2all.action")
	@ResponseBody
	public String getStockSha2All(String name, String type,
			String state, String way, Integer page, Integer size){
		System.out.println("********getstocksha2all********");
		page = page != null ? page : 1;
		size = size != null ? size : 20;
		page = (page - 1) * size;
		
		List<StockShA2> slist = stockSh2Service.findStockAAll(type, state, way, page, size);
		
		String json = JSON.toJSONString(slist);
		return addJson("成功", "1000", json);
	}
	
	/**
	 *	@author LTQ
	 *	@function 按照各股票代码显示股票列表（最新数据）
	 *  @param
	 */
	@RequestMapping("getstocksha2list.action")
	@ResponseBody
	public String getStockSha2List(String name, String type,
			String state, String way, Integer page, Integer size){
		System.out.println("********getstocksha2list********");
		page = page != null ? page : 1;
		size = size != null ? size : 20;
		page = (page - 1) * size;
		
		List<StockShA2> slist = stockSh2Service.findStockAGroupByCode(type, page, size);
		int count = stockSh2Service.findStockAGroupByCodeCount(type);
		if(slist != null)
			log.debug("slist(0).end="+slist.get(0).getEnd());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setSize(size);
		pageUtil.setCount(count);
		String json = JSON.toJSONString(slist);
		String pagejson = JSON.toJSONString(pageUtil);
		return addJson("1000", "成功", json, pagejson);
	}
	
	/**
	 *	@author LTQ
	 *	@function 统计股票的连续涨跌频率
	 *  @param
	 */
	@RequestMapping("getfrequencebycode.action")
	@ResponseBody
	public String getFrequenceByCode(String name,String code,String choose, Integer size){
		System.out.println("********getfrequencebycode********");
		int add = 0,reduce = 0,num = 0;
		log.info("分析的code="+code+",choose="+choose+",size="+size);
		List<StockShA2> slist = stockSh2Service.findStockASearch(null, null, null, name, code, null);
		List<PriceFrequence> plist = new ArrayList<PriceFrequence>();
		log.debug("priceChange(0)="+slist.get(0).getPricechange());
		for (StockShA2 stockShA2 : slist) {
			PriceFrequence priceFrequence = new PriceFrequence();
			if(stockShA2.getPricechange() != null){
				if(stockShA2.getPricechange()>0 ){
					++add;
					if(reduce != 0){
						priceFrequence.setCode(code);
						priceFrequence.setChangePoint(reduce);
						priceFrequence.setChangeNum(num);
						priceFrequence.setDate(stockShA2.getDate());
						plist.add(priceFrequence);
						reduce = 0;
					}
					num++;
					continue;
				}
				if(stockShA2.getPricechange()<0 ){
					--reduce;
					if(add != 0){
						priceFrequence.setCode(code);
						priceFrequence.setChangePoint(add);
						priceFrequence.setChangeNum(num);
						priceFrequence.setDate(stockShA2.getDate());
						plist.add(priceFrequence);
						add = 0;
					}
					num++;
					continue;
				}
				priceFrequence.setCode(code);
				priceFrequence.setChangePoint(0);
				priceFrequence.setChangeNum(num);
				priceFrequence.setDate(stockShA2.getDate());
				plist.add(priceFrequence);
				num++;
			}else{
				priceFrequence.setCode(code);
				priceFrequence.setChangePoint(0);
				priceFrequence.setChangeNum(num);
				priceFrequence.setDate(stockShA2.getDate());
				plist.add(priceFrequence);
				num++;
			}
		}
		//把连续涨跌的原始数据统计为“连续涨跌天的出现次数”，只记录有值的（不包括出现次数0的情况）
		Map<String,String> map = new HashMap<String,String>();
		List<Integer> numlistx = new LinkedList<Integer>();
		List<Integer> numlisty = new LinkedList<Integer>();
		Integer []array = new Integer[200];
		Integer []arrayx = new Integer[200];
		for(int i=-100;i<100;i++){		//数据初始化
			array[i+100] = 0;			//y轴
			arrayx[i+100] = i;			//x轴
		}
		
		
		for(PriceFrequence p: plist){
			for(int i=-100;i<100;i++){
				if(p.getChangePoint()==i){
					++array[i+100];
					map.put(Integer.toString(i), Integer.toString(array[i+100]));
					//numlistx.add(p.getChangePoint());
				}
			}
		}
		//自定义显示的尺寸，默认是从-100到99，设定后是-size到size
		for(int i=-100;i<100;i++){
			if(size != null){
				if(size == -i){
					log.debug("size="+i+"->" + size);
					for(int j=i;j <= size;j++){
						numlistx.add(arrayx[j+100]);
						numlisty.add(array[j+100]);
					}
					break;
				}
			}else{
				numlistx.add(arrayx[i+100]);
				numlisty.add(array[i+100]);
			}
		}
		
		String json = null;
		if(choose != null){
			if(choose.equals("1")){		//原始数据，包括了每一次连续天数出现的位置和日期
				json = JSON.toJSONString(plist);
			}if(choose.equals("2")){	//把连续涨跌的原始数据统计为“连续涨跌天的出现次数”，只记录有值的（不包括出现次数0的情况）
				json = JSON.toJSONString(map);
			}if(choose.equals("3")){	//进一步把map集合转为list集合，方便前台调用，且记录范围内所有值（包括出现次数0的情况）
				String numx = JSON.toJSONString(numlistx);
				json = JSON.toJSONString(numlisty);
				
				//return add2Json("1000", "成功", numx, json);
				Object j1 = JSON.toJSON(numlistx);
				Object j2 = JSON.toJSON(numlisty);
				return add2Json("1000", "成功", j1, j2);
			}
		}else{
			json = JSON.toJSONString(map);
		}
		
		
		return addJson("1000", "成功", json);
	}
	
	@RequestMapping("addStockShA")
	public String addStockShA(StockShA stockA){
		try {
			int i = stockShService.addStockA(stockA);
			return addJson("1000", "成功", "添加了"+i+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addJson("1004", "服务器异常", "{}");
	}
	
	@RequestMapping("addStockShA2")
	public String addStockShA2(StockShA stockA){
		try {
			int i = stockSh2Service.addStockA(stockA);
			return addJson("1000", "成功", "添加了"+i+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addJson("1004", "服务器异常", "{}");
	}
	
	@RequestMapping("grepstock")
	@ResponseBody
	public String grepstock(String pageNum){
		ReportUtil ru = new ReportUtil();
		int num = 0,page = 1;
		try {
			if(pageNum != null){
				page = Integer.parseInt(pageNum);
			}
			for(page = 17;page < 18;page++){
				String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page="+page+"&pageSize=200&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6646690248959264";
				List<StockShA> list = ru.findMessage(url,null,"utf-8",Integer.toString(page));
				int addnum = 0;
				log.info("page="+page);
				for (StockShA sa: list) {
					sa.setId(UUID.randomUUID().toString());
					log.info("code="+sa.getCode()+",name="+sa.getName());
					addnum = stockShService.addStockA(sa);
					num = num + addnum;
				}
				Thread.sleep(100);
			}
			log.info("成功,添加了"+num+"条");
			return addJson("1000", "成功", "添加了"+num+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addJson("1004", "服务器异常", "{}");
		
	}
}
