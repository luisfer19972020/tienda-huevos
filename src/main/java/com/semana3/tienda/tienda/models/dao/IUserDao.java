package com.semana3.tienda.tienda.models.dao;

import com.semana3.tienda.tienda.models.entity.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
}
