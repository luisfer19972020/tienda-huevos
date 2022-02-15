package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.Stock;

public interface IStockService {
    public List<Stock> findAll();

    public List<Stock> fetchStocksWithHuevo();

    public void save(Stock stock);

    public Stock findById(Long id);
    
    public Stock fetchStockWithHuevo(Long id);

    public void delete(Long id);

    public List<Huevo> getAllEggs();
}
