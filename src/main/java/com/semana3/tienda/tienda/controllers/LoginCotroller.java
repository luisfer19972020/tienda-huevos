package com.semana3.tienda.tienda.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginCotroller {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, // Es no required por que no
                                                                                       // siempre habra
            Model model,
            Principal principal,
            RedirectAttributes flash) {
        if (principal != null) {
            flash.addFlashAttribute("warning", "Ya se habias iniciado Sesion");
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        return "/login";
    }
}
