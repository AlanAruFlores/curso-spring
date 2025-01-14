package com.security.crud.conf.interceptors;

import com.security.crud.model.Task;
import com.security.crud.model.User;
import com.security.crud.service.ITaskService;
import com.security.crud.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorAuthentication implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;


    private static Logger logger = LoggerFactory.getLogger(InterceptorAuthentication.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().contains("/task") && request.getParameter("id") != null) {
            Long id  = Long.parseLong(request.getParameter("id"));
            Task task = this.taskService.getTaskById(id);
            if(task == null)
                return false;

            User currentUser = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
            if(task.getUser().getId() !=  currentUser.getId())
                return false;
        }

        return true;
    }
}
