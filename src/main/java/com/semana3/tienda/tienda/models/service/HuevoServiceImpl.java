package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.dao.IHuevoDao;
import com.semana3.tienda.tienda.models.entity.Huevo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HuevoServiceImpl implements IHuevoService {

    @Autowired
    private IHuevoDao huevoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Huevo> findAll() {
        return (List<Huevo>) huevoDao.findAll();
    }

    @Override
    @Transactional
    public void save(Huevo huevo) {
        huevoDao.save(huevo);
    }

    @Override
    @Transactional(readOnly = true)
    public Huevo findById(Long id) {
        return huevoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        huevoDao.deleteById(id);
        ;
    }

}
