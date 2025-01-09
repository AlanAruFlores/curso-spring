package com.security.crud.controller;

import com.security.crud.model.Task;
import com.security.crud.model.User;
import com.security.crud.service.ITaskService;
import com.security.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;

    @RequestMapping("/home")
    public String goHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUserName(authentication.getName());

        List<Task> taskList = taskService.getAllTaskByUser(user);
        model.addAttribute("taskList", taskList);
        return "home";
    }

}
