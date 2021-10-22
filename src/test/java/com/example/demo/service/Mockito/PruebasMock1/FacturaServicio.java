package com.example.demo.service.Mockito.PruebasMock1;

public class FacturaServicio {

    private Calculadora calculadora;

    public FacturaServicio(Calculadora calculadora){
        this.calculadora = calculadora;
    }
    public double calcularPrecio(Factura factura){

        Double precioTotal =
                factura.getPrecioBase()
                        * factura.getCantidad();
        double iva = calculadora.calcularIVA(precioTotal);
        return precioTotal+iva;
    }

}
