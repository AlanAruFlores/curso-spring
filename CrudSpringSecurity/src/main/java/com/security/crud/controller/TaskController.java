package com.security.crud.controller;

import com.security.crud.model.Task;
import com.security.crud.model.User;
import com.security.crud.service.ITaskService;
import com.security.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createTask(Model model){
        model.addAttribute("task", new Task());
        return "create-task";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public String createTask(Model model, @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUserName(authentication.getName());

        task.setUser(user);
        taskService.createTask(task);

        redirectAttributes.addFlashAttribute("flagCreate", true);
        return "redirect:/task/home";
    }

    @RequestMapping(value = "/show-task/{id}", method = RequestMethod.GET)
    public String showTask(Model model, @PathVariable("id") Long id){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "show-task";
    }

}
