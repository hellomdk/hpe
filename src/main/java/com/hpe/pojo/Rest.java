package com.hpe.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="rest")
public class Rest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer taskId;
	
	private String result;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Rest [taskId=" + taskId + ", result=" + result + "]";
	}
	
	
}
