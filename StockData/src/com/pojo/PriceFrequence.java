package com.pojo;

import java.util.Date;

/**
 * @author LTQ
 * @package com.pojo
 * @function 用于统计股价变化频率
 */
public class PriceFrequence {

	private String code;
	private Integer changePoint;		//连续涨跌的点
	private Integer changeNum;			//记录当前的数据位置（相当于id编号）
	private Date date;					//记录当前的时间
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getChangePoint() {
		return changePoint;
	}
	public void setChangePoint(Integer changePoint) {
		this.changePoint = changePoint;
	}
	public Integer getChangeNum() {
		return changeNum;
	}
	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
