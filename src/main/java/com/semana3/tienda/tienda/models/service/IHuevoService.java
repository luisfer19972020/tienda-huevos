package com.semana3.tienda.tienda.models.service;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Huevo;

public interface IHuevoService {
    public List<Huevo> findAll();

    public void save(Huevo huevo);

    public Huevo findById(Long id);

    public void delete(Long id); 
}
