package com.example.demo.service.JUnit.Conceptos;

import com.example.demo.service.JUnit.Conceptos.Concepto6.DisabledTest;
import org.junit.platform.suite.api.*;


// JUnit 5 nueva forma
// Currently not working

@Suite
@SelectClasses(DisabledTest.class)
@SelectPackages("com.example.demo.service.KarimTests.repository")
class SuiteTest5 {

}


