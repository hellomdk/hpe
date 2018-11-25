package com.hpe.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.hpe.pojo.Task;

public interface TaskMapper extends Mapper<Task>{
	/**
	 * 
	 * @Description:TODO描述：   根据taskid查询
	 * @author: 马德科
	 * @date:   2018年11月22日 下午6:06:44    
	 * @param task
	 * @return
	 */
	Task selectTask(Task task);
	
	/**
	 * 
	 * @Description:TODO描述：   查询所有 task表
	 * @author: 马德科
	 * @date:   2018年11月22日 下午6:07:56    
	 * @return
	 */
	List<Task> selectTaskList();

	void deleteAll(int[] arr);

}
