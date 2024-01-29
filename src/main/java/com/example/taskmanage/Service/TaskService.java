package com.example.taskmanage.Service;

import com.example.taskmanage.Model.Task;
import com.example.taskmanage.Model.User;

import java.util.Date;
import java.util.List;

public interface TaskService {

    Task createTask(User user, String task, String taskStatus, Date taskDate);

    void createTask(Task taskCreated);

    List<Task> getTasksByUserId(int userId);

    void deleteTaskByUserIdAndTaskId(int userId, int taskId);

    Task updateTaskByUserIdAndTaskId(int userId, int taskId, Task updatedTask);
}


