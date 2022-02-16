package com.semana3.tienda.tienda.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comprador;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;
    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venta_id")
    private List<LineaVenta> lineas;

    @PrePersist
    public void prepPersist() {
        this.createdAt = new Date();
    }

    public Venta() {
        this.lineas = new ArrayList<LineaVenta>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComprador() {
        return this.comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<LineaVenta> getLineas() {
        return this.lineas;
    }

    public void setLineas(List<LineaVenta> lineas) {
        this.lineas = lineas;
    }

    // Meotods para añadir items o lineas y calcular
    public void addItemFactura(LineaVenta linea) {
        this.lineas.add(linea);
    }

    public Double calcularTotal() {
        Double total = 0.0;

        for (LineaVenta venta : lineas) {
            total += venta.calcularImporte();
        }
        return total;
    }

}
