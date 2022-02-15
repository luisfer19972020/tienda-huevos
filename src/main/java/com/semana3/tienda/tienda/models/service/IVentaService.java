package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.entity.Stock;

public interface IVentaService {
    public List<Stock> getStocksFecthHuevo();
    public Carton findCartonById(Long id);
}
