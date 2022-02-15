package com.semana3.tienda.tienda.controllers;

import javax.validation.Valid;

import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.service.IHuevoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/huevos")
@SessionAttributes("huevo")
public class HuevoController {
    @Autowired
    private IHuevoService huevoService;

    @GetMapping({ "", "/" })
    public String index(Model model) {
        model.addAttribute("huevos", huevoService.findAll());
        return "admin/huevos/index.html";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Huevo huevo = new Huevo();
        model.addAttribute("huevo", huevo);
        return "admin/huevos/create";
    }

    @PostMapping("/store")
    public String store(@Valid Huevo huevo, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/huevos/create";
        }

        huevoService.save(huevo);
        status.setComplete();
        flash.addFlashAttribute("success", "Tipo de Huevo creado con exito!!!");
        return "redirect:/admin/huevos";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Huevo huevo = null;
        if (id > 0) {
            huevo = huevoService.findById(id);
            if (huevo == null) {
                flash.addFlashAttribute("warning", "El ID: " + id + " No existe en la base de datos!!");
                return "redirect:/admin/huevos";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/admin/huevos";
        }
        model.addAttribute("huevo", huevo);
        return "admin/huevos/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Huevo huevo, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/huevos/edit";
        }

        huevoService.save(huevo);
        status.setComplete();
        flash.addFlashAttribute("success", "Tipo de Huevo editado con exito!!!");
        return "redirect:/admin/huevos";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            huevoService.delete(id);
            flash.addFlashAttribute("success", "Tipo de Huevo eliminado con exito!!!");
        }
        return "redirect:/admin/huevos";
    }

}
