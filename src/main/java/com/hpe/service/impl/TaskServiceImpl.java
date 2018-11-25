package com.hpe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.mapper.TaskMapper;
import com.hpe.pojo.Task;
import com.hpe.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskMapper taskMapper;
	
	/**
	 * 查询所有Task表信息
	 */
	@Override
	public List<Task> TaskList() throws RuntimeException {
		Task task=new Task();
		List<Task> selectTaskList = taskMapper.select(null);
		return selectTaskList;
	}
	
	/**
	 * 根据条件查询Task表
	 */
	@Override
	public List<Task> selectTask(Task task) throws RuntimeException {
		List<Task> selectTaskList = taskMapper.select(task);
		return selectTaskList;
	}
	
	
	/**
	 * 更新task表信息
	 */
	@Override
	public int updateTask(Task task) throws RuntimeException {
		int flag = taskMapper.updateByPrimaryKeySelective(task);
		return flag;
	}
	
	/**
	 * 向Task表中插入数据
	 */
	@Override
	public int insertTask(Task task) throws RuntimeException {
		int flag = taskMapper.insertSelective(task);	
		return flag;
	}

	@Override
	public Task selectOneTask(Task task) throws RuntimeException {
		Task selectOne = taskMapper.selectOne(task);
		return selectOne;
	}
	

}
