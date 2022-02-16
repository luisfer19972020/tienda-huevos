package com.semana3.tienda.tienda.controllers;

import java.io.IOException;

import javax.validation.Valid;

import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.service.ICartonService;
import com.semana3.tienda.tienda.models.service.IUploadService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cartones")
@SessionAttributes("carton")
public class CartonController {
    @Autowired
    private ICartonService cartonService;

    @Autowired
    private IUploadService uploadService;

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
            SessionStatus status, @RequestParam(name = "file") MultipartFile foto) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Veririfca los campos al llenar el formulario!!");
            return "admin/cartones/create";
        }
        if (foto.isEmpty()) {
            model.addAttribute("error", "Agrega una foto para el carton!!");
            return "admin/cartones/create";
        }
        // Proceso para guardar la foto
        String nombreArchivo = null;
        try {// Intenta guardar el archivo del request
            nombreArchivo = uploadService.guardar(foto);
            flash.addFlashAttribute("info", "Imagen cargada correctamente".concat(nombreArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        carton.setFoto(nombreArchivo);

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
            SessionStatus status, @RequestParam(name = "file") MultipartFile foto) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/cartones/edit";
        }

        if (!foto.isEmpty()) {// Si viene una imagen

            if (uploadService.eliminar(carton.getFoto())) {
                flash.addFlashAttribute("info", "La foto del carton " + carton.getProducto() + ", "
                        + carton.getFoto() + " ha sido actualizada");
            } else {
                flash.addFlashAttribute("error", "La foto del carton " + carton.getProducto() + ", "
                        + carton.getFoto() + " no se pudo eliminar");
            }
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.guardar(foto);
                flash.addFlashAttribute("info", "Imagen cargada correctamente".concat(nombreArchivo));
            } catch (IOException e) {
                e.printStackTrace();
            }
            carton.setFoto(nombreArchivo);
        }

        cartonService.save(carton);
        status.setComplete();
        flash.addFlashAttribute("success", "Carton editado con exito!!!");
        return "redirect:/admin/cartones";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            Carton carton = cartonService.findById(id);
            cartonService.delete(id);
            flash.addFlashAttribute("success", "Carton eliminado con exito!!!");

            if (uploadService.eliminar(carton.getFoto())) {// Elimnamos el archivo
                flash.addFlashAttribute("info", "La foto del carton " + carton.getProducto() + ", "
                        + carton.getFoto() + " ha sido eliminada");
            } else {
                flash.addFlashAttribute("error", "La foto del carton " + carton.getProducto() + ", "
                        + carton.getFoto() + " no se pudo eliminar");
            }
        }
        return "redirect:/admin/cartones";
    }

}
