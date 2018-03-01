package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StockSh2Dao;
import com.pojo.StockShA;
import com.pojo.StockShA2;
import com.service.StockSh2Service;

@Service("StockSh2Service")
public class StockSh2ServiceImpl implements StockSh2Service {

	@Autowired
	private StockSh2Dao stockSh2Dao;
	
	@Override
	public StockShA2 findStockA(String id, String code, String name) {
		return stockSh2Dao.findStockA(id, code, name);
	}
	
	/**
	 * 查询全部信息列表
	 */
	@Override
	public List<StockShA2> findStockAAll(String type,
			String state, String way, Integer page, Integer size) {
		return stockSh2Dao.findStockAAll(type, state, way,page,size);
	}

	@Override
	public List<StockShA2> findStockASearch(String type,
			String state, String way, String name, String code, String info) {
		return stockSh2Dao.findStockASearch(type, state, way, name, code, info);
	}

	/**
	 * 查看某个股
	 */
	@Override
	public List<StockShA2> findStockAOne(String code, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return stockSh2Dao.findStockAOne(code, startTime, endTime);
	}

	/**
	 * 查询股票列表，且以大盘（code=000001）的最新日期为准
	 */
	@Override
	public List<StockShA2> findStockAGroupByCode(String type, Integer page,
			Integer size) {
		//SELECT * FROM stocksha2 s 
		/*  查询股票列表，且以所有股的最新日期（各股数据库中最新日期可能不同）
		RIGHT JOIN (SELECT `code`,MAX(`date`) `maxdate` FROM stocksha2 GROUP BY `CODE`) b ON s.date=b.maxdate AND s.code=b.code
		*/
		/*	查询股票列表，且以大盘（code=000001）的最新日期为准
		RIGHT JOIN (SELECT MAX(`date`) `neardate` FROM stocksha2 WHERE `code`='\'000001') c ON s.`date`=c.`neardate`;
		 */

		return stockSh2Dao.findStockAGroupByCode(type, page, size);
	}

	@Override
	public int findStockAGroupByCodeCount(String type) {
		// TODO Auto-generated method stub
		return stockSh2Dao.findStockAGroupByCodeCount(type);
	}
	
	@Override
	public int addStockA(StockShA stock) {
		// TODO Auto-generated method stub
		return stockSh2Dao.addStockA(stock);
	}

	@Override
	public int updateStockA(StockShA stock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStockA(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

	

}
