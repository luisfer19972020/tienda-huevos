package com.semana3.tienda.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministracionController {
    @GetMapping({ "admin", "admin/" })
    public String home() {
        return "admin/home";
    }

}
