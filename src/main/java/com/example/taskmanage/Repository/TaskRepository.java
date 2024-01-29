package com.example.taskmanage.Repository;

import com.example.taskmanage.Model.Task;
import com.example.taskmanage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByUserId(int userId);

    @Query("SELECT task FROM Task t JOIN t.user u where u.id = :userId")
    List<Task> findTasksByUserId(@Param("userId") int userId);

    void deleteByUserIdAndTaskId(int userId, int taskId);

    Task findByUserIdAndTaskId(int userId, int taskId);
}
