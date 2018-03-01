package com.pojo;

import java.util.Date;

public class StockShA2 {

	private String id;
	private Date date;
	private String code;
	private String name;
	private Double max;
	private Double min;
	private Double start;
	private Double end;
	private Double yesterday;
	private Double pricechange;	//涨跌额
	private Double percent;		//涨跌幅
	private Double volumn;
	private Double turnvolumn;
	private Double turnoverRate;			//换手率
	private Double marketValue;				//总市值
	private Double circulationMarketValue;	//流通市值
	private Double average182;
	private String state;		//状态
	private String type;		//交易所，股票类型
	private String info;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Double getStart() {
		return start;
	}
	public void setStart(Double start) {
		this.start = start;
	}
	public Double getEnd() {
		return end;
	}
	public void setEnd(Double end) {
		this.end = end;
	}
	public Double getYesterday() {
		return yesterday;
	}
	public void setYesterday(Double yesterday) {
		this.yesterday = yesterday;
	}
	
	public Double getPricechange() {
		return pricechange;
	}
	public void setPricechange(Double pricechange) {
		this.pricechange = pricechange;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public Double getVolumn() {
		return volumn;
	}
	public void setVolumn(Double volumn) {
		this.volumn = volumn;
	}
	public Double getTurnvolumn() {
		return turnvolumn;
	}
	public void setTurnvolumn(Double turnvolumn) {
		this.turnvolumn = turnvolumn;
	}
	public Double getAverage182() {
		return average182;
	}
	public void setAverage182(Double average182) {
		this.average182 = average182;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Double getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(Double turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public Double getCirculationMarketValue() {
		return circulationMarketValue;
	}
	public void setCirculationMarketValue(Double circulationMarketValue) {
		this.circulationMarketValue = circulationMarketValue;
	}		
	
	
	
	
}
