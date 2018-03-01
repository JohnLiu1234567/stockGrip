package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StockShDao;
import com.pojo.StockShA;
import com.service.StockShService;

@Service("StockShService")
public class StockShServiceImpl implements StockShService {

	@Autowired
	private StockShDao stockShDao;
	
	@Override
	public StockShA findStockA(String id, String code, String name) {
		
		return stockShDao.findStockA(id, code, name);
	}

	public List<StockShA> findStockAAll(String type,
			String exchange, String state, String way) {
		List<StockShA> slist = null;
		try {
			slist = stockShDao.findStockAAll(type, exchange, state, way);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slist;
	}
	
	@Override
	public List<StockShA> findStockASearch(String type, String exchange,
			String state, String way, String name, String code, String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addStockA(StockShA stock) {
		// TODO Auto-generated method stub
		return stockShDao.addStockA(stock);
	}

	@Override
	public int updateStockA(StockShA stock) {
		// TODO Auto-generated method stub
		return stockShDao.updateStockA(stock);
	}

	@Override
	public int deleteStockA(String id) {
		// TODO Auto-generated method stub
		return stockShDao.deleteStockA(id);
	}

	

}
