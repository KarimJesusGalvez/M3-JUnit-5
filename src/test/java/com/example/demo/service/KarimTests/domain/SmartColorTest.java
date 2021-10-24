package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.SmartColor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.example.demo.domain.SmartColor.SILVER_BLACK;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SmartColorTest {

    @Test
    void name() {
        SmartColor[] temp;
        assertDoesNotThrow(() ->SmartColor.values());
        temp = SmartColor.values();
        assertTrue(temp.length == 3);
        List<SmartColor> templist =Arrays.stream(temp).toList();
        assertEquals(SILVER_BLACK,templist.get(0));
    }
}
