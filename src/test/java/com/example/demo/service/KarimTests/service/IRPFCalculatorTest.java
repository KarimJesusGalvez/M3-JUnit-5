package com.example.demo.service.KarimTests.service;

import com.example.demo.service.IRPFCalculator;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("IRPF Calculator tests")
@TestClassOrder(ClassOrderer.ClassName.class)

public class IRPFCalculatorTest {

    IRPFCalculator irpfCalculator = new IRPFCalculator();

    // TODO test access level

    @Test
    void calculatePositive() {
        double a = irpfCalculator.calculateIRPF(1);
        assertEquals(0.15,a);
    }
    @Test
    void calculate0() {
        double a = irpfCalculator.calculateIRPF(0);
        assertEquals(0,a);
    }
    @Test
    void calculateNegative() {
        double a = irpfCalculator.calculateIRPF(-1);
        assertEquals(-0.15,a);
    }
    @Test
    void calculateNumchar() {
        double a = irpfCalculator.calculateIRPF('1');
        assertTrue(a > 0);
    }
    @Test
    void calculateLetterchar() {
        double a = irpfCalculator.calculateIRPF('a');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertTrue(a > 14.5);
    }
    @Test
    void calculateSymbolchar() {
        double a = irpfCalculator.calculateIRPF('\'');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertEquals(5.85,a);
    }
    @Test
    void calculateDoubleclass() {
        double a = irpfCalculator.calculateIRPF(2D);
        assertTrue(a > 0);
    }
}

