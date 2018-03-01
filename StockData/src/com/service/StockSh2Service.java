package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.StockShA;
import com.pojo.StockShA2;

public interface StockSh2Service {

	StockShA2 findStockA(@Param("id")String id,@Param("code")String code,@Param("name")String name);	
	List<StockShA2> findStockAAll(@Param("type")String type,@Param("state")String state,@Param("way")String way,@Param("page")Integer page,@Param("size")Integer size);
	List<StockShA2> findStockASearch(@Param("type")String type,@Param("state")String state,@Param("way")String way,@Param("name")String name,@Param("code")String code,@Param("info")String info);
	List<StockShA2> findStockAOne(@Param("code")String code,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	List<StockShA2> findStockAGroupByCode(@Param("type")String type,@Param("page")Integer page,@Param("size")Integer size);
	int findStockAGroupByCodeCount(@Param("type")String type);
	
	int addStockA(@Param("stock")StockShA stock);
	int updateStockA(@Param("stock")StockShA stock);
	int deleteStockA(@Param("id")String id);
	
	
	
	
	
}
