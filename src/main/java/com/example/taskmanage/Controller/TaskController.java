package com.example.taskmanage.Controller;

import com.example.taskmanage.Model.Task;
import com.example.taskmanage.Model.User;
import com.example.taskmanage.Service.TaskService;
import com.example.taskmanage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/taskManager")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;


    //User creates task/tasks
    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createTask(@RequestBody Task task, @PathVariable int userId) {
        User loggedInUser = userService.findById(userId);

        if (loggedInUser != null) {
            Task taskCreated = new Task();
            taskCreated.setUser(loggedInUser);
            taskCreated.setTask(task.getTask());
            taskCreated.setTaskDate(task.getTaskDate());
            taskCreated.setTaskStatus(task.getTaskStatus());

            taskService.createTask(taskCreated);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }


    //Displaying the task/tasks of the user
    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable int userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);

            return ResponseEntity.ok(tasks);
    }


    //User deletes a task
    @DeleteMapping("/tasks/{userId}/{taskId}")
    public ResponseEntity<String> deleteTaskByUserIdAndTaskId(@PathVariable int userId, @PathVariable int taskId) {
        taskService.deleteTaskByUserIdAndTaskId(userId, taskId);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }


    //User updates a task
    @PutMapping("/tasks/{userId}/{taskId}")
    public ResponseEntity<Task> updateTaskByUserIdAndTaskId(
            @PathVariable int userId,
            @PathVariable int taskId,
            @RequestBody Task updatedTask) {

        Task updatedTaskResult = taskService.updateTaskByUserIdAndTaskId(userId, taskId, updatedTask);

        if (updatedTaskResult != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTaskResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}

