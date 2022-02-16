package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.Stock;
import com.semana3.tienda.tienda.models.entity.Venta;

public interface IVentaService {
    public List<Stock> getStocksFecthHuevo();

    public Carton findCartonById(Long id);

    public List<Huevo> findHuevoByTipoHuevo(String term);

    public Huevo findHuevoById(Long id);

    public void save(Venta venta);

    public List<Venta> findAll();

    public Venta findById(Long id);

    public Venta fetchVentaWithLineasWithHuevos(Long id);

    public void descuentaStock(Long id, Integer cantidad);

}
