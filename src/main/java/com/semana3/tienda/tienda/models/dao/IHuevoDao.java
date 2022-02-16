package com.semana3.tienda.tienda.models.dao;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Huevo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IHuevoDao extends CrudRepository<Huevo, Long> {
    @Query("select h from Huevo h where h.tipoHuevo like %?1%")
    public List<Huevo> findByTipoHuevo(String term);
}
