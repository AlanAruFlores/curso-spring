package com.security.crud.service;


import com.security.crud.model.Task;
import com.security.crud.model.User;

import java.util.List;

public interface ITaskService {
    void createTask(Task task);
    List<Task> getAllTaskByUser(User user);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    void deleteTaskById(Long id);
}
