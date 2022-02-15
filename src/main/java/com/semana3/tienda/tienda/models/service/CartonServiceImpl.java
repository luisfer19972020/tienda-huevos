package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.ICartonDao;
import com.semana3.tienda.tienda.models.entity.Carton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartonServiceImpl implements ICartonService {

    @Autowired
    private ICartonDao cartonDao;

    @Override
    @Transactional(readOnly = true)
    public List<Carton> findAll() {
        return (List<Carton>) cartonDao.findAll();
    }

    @Override
    @Transactional
    public void save(Carton carton) {
        cartonDao.save(carton);        
    }

    @Override
    @Transactional(readOnly = true)
    public Carton findById(Long id) {
        return cartonDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartonDao.deleteById(id);        
    }
    
}
