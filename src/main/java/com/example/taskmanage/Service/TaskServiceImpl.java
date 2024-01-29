package com.example.taskmanage.Service;

import com.example.taskmanage.Model.Task;
import com.example.taskmanage.Model.User;
import com.example.taskmanage.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl  implements TaskService{

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task createTask(User user, String task, String taskStatus, Date taskDate) {
        Task newTask = new Task();
        newTask.setUser(user);
        newTask.setTask(task);
        newTask.setTaskDate(taskDate);
        newTask.setTaskStatus(taskStatus);

        return taskRepository.save(newTask);
    }


    @Override
    public void createTask(Task taskCreated) {
        taskRepository.save(taskCreated);
    }


    @Override
    public List<Task> getTasksByUserId(int userId) {
        List<Task> np = taskRepository.findAllByUserId(userId);
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteTaskByUserIdAndTaskId(int userId, int taskId) {
        taskRepository.deleteByUserIdAndTaskId(userId, taskId);
    }

    @Override
    public Task updateTaskByUserIdAndTaskId(int userId, int taskId, Task updatedTask) {
        Task existingTask = taskRepository.findByUserIdAndTaskId(userId, taskId);

        if (existingTask != null) {
            // Update the existing task with the new information
            existingTask.setTask(updatedTask.getTask());
            existingTask.setTaskStatus(updatedTask.getTaskStatus());

            // Save the updated task
            return taskRepository.save(existingTask);
        } else {
            // Task not found
            return null;
        }
    }
}
