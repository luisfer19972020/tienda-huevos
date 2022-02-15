package com.semana3.tienda.tienda.models.dao;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStockDao extends CrudRepository<Stock, Long> {

    @Query("select s from Stock s join fetch s.huevo h")
    public List<Stock> fetchStocksWithHuevo();

    @Query("select s from Stock s join fetch s.huevo h where s.id=?1")
    public Stock fetchStockWithHuevo(Long id);
}
