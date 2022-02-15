package com.semana3.tienda.tienda.controllers;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Stock;
import com.semana3.tienda.tienda.models.service.IVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping("/comprar/{id}")
    public String comprar(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("stocks", ventaService.getStocksFecthHuevo());
        model.addAttribute("carton", ventaService.findCartonById(id));
        return "clie/compras/create";
    }

    @GetMapping(value = "/prueba")
    public @ResponseBody List<Stock> getStock() {

        return ventaService.getStocksFecthHuevo();
    }
}
