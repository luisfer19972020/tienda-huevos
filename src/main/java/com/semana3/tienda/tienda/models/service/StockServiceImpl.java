package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.IHuevoDao;
import com.semana3.tienda.tienda.models.dao.IStockDao;
import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements IStockService {
    @Autowired
    private IStockDao stockDao;
    @Autowired
    private IHuevoDao huevoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Stock> findAll() {
        return (List<Stock>) stockDao.findAll();
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        stockDao.save(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public Stock findById(Long id) {
        return stockDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Stock fetchStockWithHuevo(Long id) {
        return stockDao.fetchStockWithHuevo(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Stock> fetchStocksWithHuevo() {
        return stockDao.fetchStocksWithHuevo();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Huevo> getAllEggs() {
        return (List<Huevo>) huevoDao.findAll();
    }

}
