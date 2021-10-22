package com.example.demo.service.Mockito.PruebasMock1;

public class Factura {

    private Long id;
    private Double precioBase;
    private Integer cantidad;
    private Double precioEnvio;

    public Factura(){}

    public Factura(Long id, Double precioBase, Integer cantidad, Double precioEnvio) {
        this.id = id;
        this.precioBase = precioBase;
        this.cantidad = cantidad;
        this.precioEnvio = precioEnvio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(Double precioEnvio) {
        this.precioEnvio = precioEnvio;
    }
}
