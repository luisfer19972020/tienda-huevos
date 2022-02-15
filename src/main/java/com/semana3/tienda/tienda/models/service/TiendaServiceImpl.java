package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.ICartonDao;
import com.semana3.tienda.tienda.models.entity.Carton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TiendaServiceImpl implements ITiendaService {

    @Autowired
    private ICartonDao cartonDao;

    @Override
    @Transactional(readOnly = true)
    public List<Carton> getAllCartones() {
        return (List<Carton>) cartonDao.findAll();
    }

}
