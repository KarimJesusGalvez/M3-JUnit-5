package com.example.demo.service.Mockito.PruebasMock1;

import com.example.demo.domain.Employee;
import com.example.demo.service.IRPFCalculator;
import com.example.demo.service.IVACalculator;
import com.example.demo.service.SalaryCalculatorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Mock
    IRPFCalculator irpfCalculator;
    @Mock
    IVACalculator ivaCalculator;

    @InjectMocks
    SalaryCalculatorService service;

    @BeforeEach
    void setUp() {

        //irpfCalculator = mock(IRPFCalculator.class)
        //ivaCalculator = mock(IVACalculator.class)

        service = new SalaryCalculatorService(irpfCalculator,ivaCalculator);
    }

    @Test
    void name() {

        when(irpfCalculator.calculateIRPF(anyDouble())).thenReturn(4950D);
        when(ivaCalculator.calculateIVA(anyDouble())).thenReturn(4950D);

        Employee employee = new Employee(1L,"Prueba",30);
        double result = service.calculateSalary(employee);

        assertEquals(42900.0,result);
        verify(irpfCalculator).calculateIRPF(anyDouble());
                verify(ivaCalculator).calculateIVA(anyDouble());

    }

    @Test
    void orderTest(){

        when(irpfCalculator.calculateIRPF(anyDouble())).thenReturn(4950D);
        when(ivaCalculator.calculateIVA(anyDouble())).thenReturn(4950D);

        Employee employee = new Employee(1L,"Prueba",30);
        double result = service.calculateSalary(employee);

        assertEquals(42900.0,result);

        InOrder order = inOrder(ivaCalculator,irpfCalculator);

        order.verify(irpfCalculator).calculateIRPF(anyDouble());
        order.verify(ivaCalculator).calculateIVA(anyDouble());
        /*
         order.verify(ivaCalculator).calculateIVA(anyDouble());
        order.verify(irpfCalculator).calculateIRPF(anyDouble());


         */

    }
}
