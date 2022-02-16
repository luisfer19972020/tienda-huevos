package com.semana3.tienda.tienda.models.dao;

import java.util.List;

import com.semana3.tienda.tienda.models.entity.Venta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IVentaDao extends CrudRepository<Venta, Long> {
    @Query("select v from Venta v join fetch v.usuario u join fetch v.lineas l join fetch l.huevo h where v.id=?1")
    public Venta fetchVentaWithLineasWithHuevos(Long id);
    
    @Query("select v from Venta v join fetch v.usuario")
    public List<Venta> fetchVentaWithUser();
}
