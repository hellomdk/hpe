package com.hpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hpe.pojo.Rest;
import com.hpe.pojo.Task;
import com.hpe.service.RestService;
import com.hpe.service.TaskService;

@Controller
@RequestMapping("rest")
public class RestController {
	@Autowired
	private RestService restService;
	@Autowired
	private TaskService taskService;
	JSONObject json=new  JSONObject();
	
	@RequestMapping(value="/restList",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String CartList() {
		List<Rest> restList=null;
		try {
			restList = restService.restList();
			json.put("code", "200");
			json.put("page", restList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		return json.toString();
	}
	
	@RequestMapping(value="/selectRest",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String RestList(int id) {
		Rest rests=null;
		Rest rest=new Rest();
		Task task=new Task();
		rest.setTaskId(id);
		task.setTaskId(id);
		try {
			task = taskService.selectOneTask(task);
			rests = restService.selectRest(rest);
			json.put("code", "0");
			json.put("info", task);
			json.put("result", rests.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "500");
		}
		return json.toString();
	}
}
