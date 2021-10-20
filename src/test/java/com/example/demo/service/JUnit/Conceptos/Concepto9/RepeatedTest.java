package com.example.demo.service.JUnit.Conceptos.Concepto9;


import org.junit.jupiter.api.Test;

public class RepeatedTest {


    @Test
    void name(){}

    @org.junit.jupiter.api.RepeatedTest(value=5)
    void test4(){}

    @org.junit.jupiter.api.RepeatedTest(value = 5)
    void test10(){
        System.out.println("Ejecutando test");
    }
    @org.junit.jupiter.api.RepeatedTest(value = 10)
    void test1(){
        System.out.println("Ejecutando test");
    }


}
