package com.hpe.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hpe.pojo.Task;
import com.hpe.service.TaskService;

@Controller
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	JSONObject json=new  JSONObject();
	
	@RequestMapping(value="/taskList",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String TaskList() {
		List<Task> taskList=null;
		try {
			taskList = taskService.TaskList();
			json.put("code", "0");
			json.put("data", taskList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		return json.toString();
		//return json.toJSONString(taskList);
	}
	
	
	
	@RequestMapping(value="/selectTask",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectTask(String taskStatus) {
		List<Task> taskList=null;
		try {
			Task task=new Task();
			task.setTaskStatus(taskStatus);
			taskList = taskService.selectTask(task);
			json.put("code", "200");
			json.put("page", taskList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		return json.toString();
	}
	
	@RequestMapping(value="/updateTask",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateTask(String taskStatus) {
		int flag=0;
		Task task=new Task();
		try {
			task.setTaskStatus(taskStatus);
			flag = taskService.updateTask(task);
			json.put("code", "200");
			json.put("page", flag);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		return json.toString();
	}
	
	@RequestMapping(value="/submit",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertTask(String sort,String count) {
		String taskParam="{\"sort\":\""+sort+"\",\"count\":\""+count+"\"}";
		Task task=new Task();	
		int flag=0;
		try {
			//获取当前时间
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestring = df.format(date);
			
			task.setTaskType("WordCount计算");
			task.setCreateTime(datestring);
			task.setStartTime(datestring);
			task.setTaskName("wc");
			
			task.setTaskStatus("正在执行");
			task.setTaskParam(taskParam);
			
			flag = taskService.insertTask(task);
			
			//获取taskId
			Integer taskId = task.getTaskId();
			
			//调用linux命令  传值taskId
			//executeLinuxCmd("cd /home");
			
			Thread thread = new MyThread(taskId);
			thread.start();
			
			//executeLinuxCmd("spark-submit --master spark://node01:7077 --class com.hpe.test.WC /opt/software/spark-1.6.3/lib/WC.jar "+taskId+" --driver-class-path /opt/software/spark-1.6.3/lib/mysql-connector-java-5.1.32-bin.jar");
			//获取当前完成时间，更新task表状态
			Date currentdate=new Date();
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestrings = dfs.format(currentdate);
			task.setFinishTime(datestrings);
			task.setTaskStatus("完成");
			int updateTask = taskService.updateTask(task);
			
			json.put("code", "200");
			json.put("id", taskId);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		

		return json.toString();
	}
	
	
	public static String executeLinuxCmd(String cmd) {
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(cmd);
			InputStream in = process.getInputStream();
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[8192];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			System.out.println("job result [" + out.toString() + "]");
			in.close();
			process.waitFor();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
