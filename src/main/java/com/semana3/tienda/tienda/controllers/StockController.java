package com.semana3.tienda.tienda.controllers;

import java.util.List;

import javax.validation.Valid;

import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.Stock;
import com.semana3.tienda.tienda.models.service.IStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin/stocks")
@SessionAttributes("stock")
public class StockController {
   
    @Autowired
    private IStockService stockService;
    @GetMapping({ "", "/" })
    public String index(Model model) {
        model.addAttribute("stocks", stockService.fetchStocksWithHuevo());
        return "admin/stocks/index.html";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Stock stock = new Stock();
        model.addAttribute("stock", stock);
        return "admin/stocks/create";
    }

    @PostMapping("/store")
    public String store(@Valid Stock stock, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/stocks/create";
        }
        stockService.save(stock);
        status.setComplete();
        flash.addFlashAttribute("success", "Stock creado con exito!!!");
        return "redirect:/admin/stocks";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Stock stock = null;
        if (id > 0) {
            stock = stockService.fetchStockWithHuevo(id);
            if (stock == null) {
                flash.addFlashAttribute("warning", "El ID: " + id + " No existe en la base de datos!!");
                return "redirect:/admin/stocks";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/admin/stocks";
        }
        model.addAttribute("stock", stock);
        return "admin/stocks/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Stock stock, BindingResult result, RedirectAttributes flash, Model model,
            SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("Error", "Veririfca los campos al llenar el formulario!!");
            return "admin/stocks/edit";
        }

        stockService.save(stock);
        status.setComplete();
        flash.addFlashAttribute("success", "Stock editado con exito!!!");
        return "redirect:/admin/stocks";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            stockService.delete(id);
            flash.addFlashAttribute("success", "Stock eliminado con exito!!!");
        }
        return "redirect:/admin/stocks";
    }

    @ModelAttribute("huevos")
    public List<Huevo> huevos(){
        return stockService.getAllEggs();
    }

}
