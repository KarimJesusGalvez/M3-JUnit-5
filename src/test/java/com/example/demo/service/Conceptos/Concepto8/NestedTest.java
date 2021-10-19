package com.example.demo.service.Conceptos.Concepto8;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTest {
    @Test
    public void test1(){}

    @Nested
    class RetrieveTests{ // Not a child class !!
        @Test
        public void test1(){}
    }
    @Nested()
    class SaveTests{

    }
}
