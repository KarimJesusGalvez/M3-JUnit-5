package com.example.demo.service.JUnit.Conceptos.Concepto9;

import com.example.demo.domain.SmartColor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

/**
 * Data entry based tests
 */
public class ParameterizedTest {

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource({
            "1, Empleado1, 40, 8700",
            "2, Empleado2, 30, 2113000",
            "3, Empleado3, 20, 345000",
            "4, Empleado4, 5760, 35000",

    })
    void test1(Long id, String name, int age) {
        System.out.println(id + " "+ name+ " "+age);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @CsvFileSource(resources ="/employees.csv")
    void test2(Long id, String name, int age) {
        System.out.println(id + " "+ name+ " "+age);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(SmartColor.class)
    void name(SmartColor color) {
        System.out.println(color);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("getNames")
    void test3(String name){
        System.out.println(name);
    }
    public static List<String> getNames(){
        return List.of("Nombre1","Nombre2","Nombre3");
    }

}
