package com.hpe.service;

import java.util.List;
import java.util.Map;

import com.hpe.pojo.Task;

public interface TaskService {
	List<Task> TaskList() throws RuntimeException;

	int updateTask(Task task) throws RuntimeException;

	List<Task> selectTask(Task task) throws RuntimeException;
	
	Task selectOneTask(Task task) throws RuntimeException;

	int insertTask(Task task) throws RuntimeException;
}
