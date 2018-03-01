package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.StockShA;

public interface StockShService {

	StockShA findStockA(@Param("id")String id,@Param("code")String code,@Param("name")String name);	
	List<StockShA> findStockAAll(@Param("type")String type,@Param("exchange")String exchange,@Param("state")String state,@Param("way")String way);
	List<StockShA> findStockASearch(@Param("type")String type,@Param("exchange")String exchange,@Param("state")String state,@Param("way")String way,@Param("name")String name,@Param("code")String code,@Param("info")String info);

	int addStockA(@Param("stock")StockShA stock);
	int updateStockA(@Param("stock")StockShA stock);
	int deleteStockA(@Param("id")String id);
	
	
	
	
	
}
