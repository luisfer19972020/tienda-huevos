package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.ICartonDao;
import com.semana3.tienda.tienda.models.dao.IStockDao;
import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.entity.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private IStockDao stockDao;

    @Autowired
    private ICartonDao cartonDao;

    @Override
    @Transactional(readOnly = true)
    public List<Stock> getStocksFecthHuevo() {
        return (List<Stock>) stockDao.fetchStocksWithHuevo();
    }

    @Override
    public Carton findCartonById(Long id) {
        return cartonDao.findById(id).orElse(null);
    }

}
