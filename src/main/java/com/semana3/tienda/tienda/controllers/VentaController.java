package com.semana3.tienda.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.LineaVenta;
import com.semana3.tienda.tienda.models.entity.Venta;
import com.semana3.tienda.tienda.models.service.IVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping({ "/admin/ventas/", "/admin/ventas" })
    public String index(Model model) {
        model.addAttribute("ventas", ventaService.findAll());
        return "admin/ventas/index.html";
    }

    @GetMapping("/comprar/{id}")
    public String comprar(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("stocks", ventaService.getStocksFecthHuevo());
        model.addAttribute("carton", ventaService.findCartonById(id));
        return "clie/compras/index";
    }

    @GetMapping(value = "/ventas/cargar-huevos/{term}", produces = { "application/json" })
    public @ResponseBody List<Huevo> cargarEmpleados(@PathVariable(name = "term") String term) {
        return ventaService.findHuevoByTipoHuevo(term);
    }

    @PostMapping("/ventas/store")
    public String guardarVenta(
            @RequestParam(name = "huevo_id[]", required = false) Long[] huevo_id,
            @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
            @RequestParam(name = "carton_id", required = false) Long id,
            RedirectAttributes flash,
            Model model,
            Authentication auth) {

        if (huevo_id == null || huevo_id.length == 0) {
            model.addAttribute("status", 0);
            model.addAttribute("stocks", ventaService.getStocksFecthHuevo());
            model.addAttribute("carton", ventaService.findCartonById(id));
            return "clie/compras/index";
        }
        Venta venta = new Venta();
        venta.setComprador(auth.getName());
        for (int i = 0; i < huevo_id.length; i++) {
            LineaVenta linea = new LineaVenta();
            linea.setCantidad(cantidad[i]);
            linea.setHuevo(ventaService.findHuevoById(huevo_id[i]));
            venta.addItemFactura(linea);
            ventaService.descuentaStock(huevo_id[i], cantidad[i]);
        }
        ventaService.save(venta);
        flash.addFlashAttribute("success", "Compra realizada con exito con exito!!!");
        flash.addFlashAttribute("status", 1);
        return "redirect:/comprar/" + id;
    }

    @GetMapping("/admin/ventas/show/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Venta venta = null;
        if (id > 0) {
            venta = ventaService.fetchVentaWithLineasWithHuevos(id);
            if (venta == null) {
                flash.addFlashAttribute("warning", "El ID: " + id + " No existe en la base de datos!!");
                return "redirect:/admin/ventas";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/admin/ventas";
        }
        model.addAttribute("venta", venta);
        return "admin/ventas/details";
    }
}
