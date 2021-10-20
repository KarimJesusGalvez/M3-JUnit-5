package com.example.demo.service.JUnit.Conceptos;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.example.demo.service.JUnit.Conceptos")
@IncludeTags("database")

public class SuiteTest4 {
}
