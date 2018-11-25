package com.hpe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.mapper.RestMapper;
import com.hpe.pojo.Rest;
import com.hpe.service.RestService;

@Service
public class RestServiceImpl implements RestService{
	@Autowired
	private RestMapper restMapper;
	
	/**
	 * 查询Rest表所有信息
	 */
	@Override
	public List<Rest> restList() {
		List<Rest> select = restMapper.select(null);
		return select;
	}
	
	/**
	 * 根据条件查询Rest表信息
	 */
	@Override
	public Rest selectRest(Rest rest) {
		Rest selectOne = restMapper.selectOne(rest);
		return selectOne;
	}
	
	/**
	 * 向Rest表插入信息
	 */
	@Override
	public int insertRest(Rest rest) {
		int flag = restMapper.insertSelective(rest);
		return flag;
	}
	
}
