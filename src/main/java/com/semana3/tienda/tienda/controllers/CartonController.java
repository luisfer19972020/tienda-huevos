package com.semana3.tienda.tienda.controllers;

import javax.validation.Valid;

import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.service.ICartonService;

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
@RequestMapping("/admin/cartones")
@SessionAttributes("carton")
public class CartonController {
    @Autowired
    private ICartonService cartonService;
    @GetMapping({ "", "/" })
    public String index(Model model) {
        model.addAttribute("cartones", cartonService.findAll());
        return "admin/cartones/index.html";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Carton carton = new Carton();
        model.addAttribute("carton", carton);
        return "admin/cartones/create";
    }

    @PostMapping("/store")
    public String store(@Valid Carton carton, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/cartones/create";
        }
        cartonService.save(carton);
        status.setComplete();
        flash.addFlashAttribute("success", "Carton creado con exito!!!");
        return "redirect:/admin/cartones";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Carton carton = null;
        if (id > 0) {
            carton = cartonService.findById(id);
            if (carton == null) {
                flash.addFlashAttribute("warning", "El ID: " + id + " No existe en la base de datos!!");
                return "redirect:/admin/cartones";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/admin/cartones";
        }
        model.addAttribute("carton", carton);
        return "admin/cartones/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Carton carton, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/cartones/edit";
        }

        cartonService.save(carton);
        status.setComplete();
        flash.addFlashAttribute("success", "Carton editado con exito!!!");
        return "redirect:/admin/cartones";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            cartonService.delete(id);
            flash.addFlashAttribute("success", "Carton eliminado con exito!!!");
        }
        return "redirect:/admin/cartones";
    }

}
