package com.util;

public class PageUtil {
	
	/**
	 * 总页数
	 */
	private int total = 0;
	/**
	 * 当前页
	 */
	private int page = 0;
	/**
	 * 总条数
	 */
	private int count = 1;
	/**
	 * 页面尺寸
	 */
	private int size = 1;
	
	public int getTotal() {
		return total = count%size==0 ? count/size:(count/size)+1;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
