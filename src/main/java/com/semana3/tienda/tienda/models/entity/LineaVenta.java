package com.semana3.tienda.tienda.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "lineas")
public class LineaVenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "huevo_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Huevo huevo;

    private static final long serialVersionUID = 1L;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Huevo getHuevo() {
        return this.huevo;
    }

    public void setHuevo(Huevo huevo) {
        this.huevo = huevo;
    }

    public Double calcularImporte(){
        return cantidad.doubleValue()*this.huevo.getPrecio();
    }

}
