package hpe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hpe.mapper.TaskMapper;
import com.hpe.pojo.Task;
import com.hpe.service.TaskService;
import com.hpe.service.impl.TaskServiceImpl;

public class Test {

//	@org.junit.Test
//	public void test1() {
//		TaskService taskService=new TaskServiceImpl();
//		List<Task> taskList = taskService.TaskList();
//		for (Task task : taskList) {
//			System.out.println(task);
//	}
//	}
	
	@Autowired
	private static TaskService taskService;
	
	public static void main(String[] args) {
		List<Task> taskList = taskService.TaskList();
		for (Task task : taskList) {
			System.out.println(task);
		}
	}
}
