package com.npeetha.mytask.handler;

import java.util.List;

import com.mongodb.MongoClient;
import com.npeetha.mytask.bo.Task;

public class Handler implements IHandler {

	MongoClient client;
	

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getTask(int taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Task task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Task task) {
		// TODO Auto-generated method stub
		return false;
	}

}
