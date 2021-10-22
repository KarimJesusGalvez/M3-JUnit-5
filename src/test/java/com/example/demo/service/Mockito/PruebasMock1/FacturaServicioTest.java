package com.example.demo.service.Mockito.PruebasMock1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

class FacturaServicioTest {

    @Test
    void calcularPrecio() {

        Calculadora calculadora = new Calculadora();
        FacturaServicio facturaServicio = new FacturaServicio(calculadora);

        Factura factura = new Factura(1L,20D,2,5D);

        double result = facturaServicio.calcularPrecio(factura);

        //assertEquals(result,);
    }

    @Test
    void calcularPrecioMock() {

        Calculadora calculadoraMock = mock(Calculadora.class);
        FacturaServicio facturaServicio = new FacturaServicio(calculadoraMock);

        when(calculadoraMock.calcularIVA(anyDouble())).thenReturn(21.0);


        Factura factura = new Factura(1L,20D,5,5D);
        double result = facturaServicio.calcularPrecio(factura);

        assertEquals(121,result);

        //Checknumber of executions
        verify(calculadoraMock,times(1)).calcularIVA(4D);
        //verify(calculadoraMock,never()).calcularIVAAnother(4D);
    }
}