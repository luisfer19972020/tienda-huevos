package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Carton;

public interface ICartonService {
    public List<Carton> findAll();

    public void save(Carton carton);

    public Carton findById(Long id);

    public void delete(Long id);
}
