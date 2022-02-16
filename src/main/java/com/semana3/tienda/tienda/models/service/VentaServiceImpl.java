package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.ICartonDao;
import com.semana3.tienda.tienda.models.dao.IHuevoDao;
import com.semana3.tienda.tienda.models.dao.IStockDao;
import com.semana3.tienda.tienda.models.dao.IUserDao;
import com.semana3.tienda.tienda.models.dao.IVentaDao;
import com.semana3.tienda.tienda.models.entity.Carton;
import com.semana3.tienda.tienda.models.entity.Huevo;
import com.semana3.tienda.tienda.models.entity.Stock;
import com.semana3.tienda.tienda.models.entity.Usuario;
import com.semana3.tienda.tienda.models.entity.Venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private IStockDao stockDao;

    @Autowired
    private ICartonDao cartonDao;

    @Autowired
    private IHuevoDao huevoDao;

    @Autowired
    private IVentaDao ventaDao;
    
    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<Stock> getStocksFecthHuevo() {
        return (List<Stock>) stockDao.fetchStocksWithHuevo();
    }

    @Override
    @Transactional(readOnly = true)
    public Carton findCartonById(Long id) {
        return cartonDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Huevo> findHuevoByTipoHuevo(String term) {
        return huevoDao.findByTipoHuevo(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Huevo findHuevoById(Long id) {
        return huevoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Venta venta) {
        ventaDao.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return (List<Venta>) ventaDao.findAll();
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Venta> fetchVentaWithUser() {
        return ventaDao.fetchVentaWithUser();
    }


    @Override
    @Transactional(readOnly = true)
    public Venta findById(Long id) {
        return ventaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void descuentaStock(Long id, Integer cantidad) {
        Stock stock = stockDao.findByHuevoId(id);
        stock.setCantidad(stock.getCantidad() - cantidad);
        stockDao.save(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta fetchVentaWithLineasWithHuevos(Long id) {
        return ventaDao.fetchVentaWithLineasWithHuevos(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
