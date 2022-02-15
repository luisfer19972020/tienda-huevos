package com.semana3.tienda.tienda.controllers;

import com.semana3.tienda.tienda.models.service.ITiendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaController {
    @Autowired
    private ITiendaService tiendaService;

    @GetMapping({ "", "/" })
    public String home(Model model) {
        model.addAttribute("cartones", tiendaService.getAllCartones());
        return "clie/layouts/layout";
    }
}
