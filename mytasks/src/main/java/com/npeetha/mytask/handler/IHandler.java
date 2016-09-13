package com.npeetha.mytask.handler;

import java.util.List;

import com.npeetha.mytask.bo.Task;

public interface IHandler {

	List<Task> getTasks();
	
	Task getTask(int taskId);
	
	boolean save(Task task);
	
	boolean update(Task task);
}
