package com.security.crud.controller;

import com.security.crud.model.User;
import com.security.crud.repository.UserRepository;
import com.security.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goRegistroPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registro";
    }


    @PostMapping(value = "/post")
    public String postRegistro(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes){
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("registroExitoso", true);
        return "redirect:/registro/";
    }
}
