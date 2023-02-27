package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatTest {
    @Mock
    Feline mockedFeline;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSoundReturnMeow() {
        Cat cat = new Cat(mockedFeline);
        String expected = "Мяу";
        String actual = cat.getSound();
        assertEquals("Кошка не мяучит!", expected, actual);
    }

    @Test
    public void getFoodReturnListOfPredatorFood() throws Exception {
        Cat cat = new Cat(mockedFeline);
        Mockito.when(mockedFeline.eatMeat()).thenReturn(TestData.PREDATOR_FOOD);
        List expected = TestData.PREDATOR_FOOD;
        List actual = cat.getFood();
        assertEquals("Не еда хищников!", expected, actual);
    }

}