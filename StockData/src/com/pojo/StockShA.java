package com.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class StockShA {

	private String id;
	private String code;
	private String name;		//最新股票名称
	private String type;		//类型
	private String exchange;	//交易所
	private Double price;		//价格
	private Double pricechange;	//涨跌额
	private String percent;		//涨跌幅
	private String swing;		//振幅
	private String volume;		//成交量
	private String turnvolume;	//成交额
	private Double yesterday;	//昨收
	private Double start;		//开盘
	private Double max;			//最高
	private Double min;			//最低
	private String state;		//页数信息
	private String info;		//时间信息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPricechange() {
		return pricechange;
	}
	public void setPricechange(Double pricechange) {
		this.pricechange = pricechange;
	}
	
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getSwing() {
		return swing;
	}
	public void setSwing(String swing) {
		this.swing = swing;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTurnvolume() {
		return turnvolume;
	}
	public void setTurnvolume(String turnvolume) {
		this.turnvolume = turnvolume;
	}
	public Double getYesterday() {
		return yesterday;
	}
	public void setYesterday(Double yesterday) {
		this.yesterday = yesterday;
	}
	public Double getStart() {
		return start;
	}
	public void setStart(Double start) {
		this.start = start;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 *	@author LTQ
	 *	@function 用于把爬虫获取的数组转为实体类型
	 *  @param
	 */
	public StockShA setStockA(String [] array,String page){
		this.setType(array[0]);
		this.setCode(array[1]);
		this.setName(array[2]);
		if(!array[3].equals("-")){
			this.setPrice(Double.parseDouble(array[3]));
		}if(!array[4].equals("-")){
			this.setPricechange(Double.parseDouble(array[4]));
		}if(!array[9].equals("-")){
			this.setYesterday(Double.parseDouble(array[9]));
		}if(!array[10].equals("-")){
			this.setStart(Double.parseDouble(array[10]));
		}if(!array[11].equals("-")){
			this.setMax(Double.parseDouble(array[11]));
		}if(!array[12].equals("-")){
			this.setMin(Double.parseDouble(array[12]));
		}
		this.setPercent(array[5]);
		this.setSwing(array[6]);
		this.setVolume(array[7]);
		this.setTurnvolume(array[8]);
		this.setExchange("上海证券交易所");
		this.setState(page);
		this.setInfo(new Date().toString());
		return this;
		
	}
	
	/**
	 *	@author LTQ
	 *	@function 用于把爬虫获取的hashMap集合转为实体类型
	 *  @param
	 */
	public StockShA setStockAFromHashMap(HashMap<String, Object> hashMap){
		String s = hashMap.toString().replace("		", "");
		
		int i = 0;
		this.setId(UUID.randomUUID().toString());
		this.setCode(hashMap.get("编号").toString());
		this.setName(hashMap.get("股票名").toString());
		this.setType(hashMap.get("类型").toString());
		if(hashMap.get("最新价").toString().indexOf("-") < 0){
			this.setPrice(Double.parseDouble(hashMap.get("最新价").toString()));
		}if(hashMap.get("涨跌额").toString().indexOf("-") < 0){
			this.setPricechange(Double.parseDouble(hashMap.get("涨跌额").toString()));
		}if(hashMap.get("昨收		").toString().indexOf("-") < 0){
			this.setYesterday(Double.parseDouble(hashMap.get("昨收	").toString()));
		}if(hashMap.get("今开		").toString().indexOf("-") < 0){
			this.setStart(Double.parseDouble(hashMap.get("今开	").toString()));
		}if(hashMap.get("最高").toString().indexOf("-") < 0){
			this.setMax(Double.parseDouble(hashMap.get("最高").toString()));
		}if(hashMap.get("最低").toString().indexOf("-") < 0){
			this.setMin(Double.parseDouble(hashMap.get("最低").toString()));
		}
		this.setPercent(hashMap.get("涨跌幅").toString());
		this.setSwing(hashMap.get("振幅").toString());
		this.setVolume(hashMap.get("成交量(手)").toString());
		this.setTurnvolume(hashMap.get("成交额(万)").toString());
		this.setExchange("上海证券交易所");
		this.setState("");
		this.setInfo(new Date().toString());
		return this;
	}
	
}
