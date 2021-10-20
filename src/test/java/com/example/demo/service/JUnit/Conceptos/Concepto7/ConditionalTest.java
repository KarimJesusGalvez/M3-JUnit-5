package com.example.demo.service.JUnit.Conceptos.Concepto7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConditionalTest {

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void test1(){}
    @EnabledOnOs(OS.LINUX)
    @Test
    void test2(){}
    @EnabledOnJre(JRE.JAVA_17)
    @Test
    void test3(){
        System.out.println("test2");
        assertTrue(true);
    }
    @EnabledForJreRange(min=JRE.JAVA_8, max= JRE.JAVA_11)
    @Test
    void test4(){
        System.out.println("test2");
        assertTrue(true);
    }
    @Test
    void printEnv(){
       // Imprime propiedades del sistema
        System.getProperties().forEach((key,value)-> System.out.println(key + "" + value));
    }
    @EnabledIfEnvironmentVariable(named= "USERNAME", matches= "testing")
    @Test
    void Test56(){
        // Runs if variable is ...
        System.getProperties().forEach((key,value)-> System.out.println(key + "" + value));
    }
}
