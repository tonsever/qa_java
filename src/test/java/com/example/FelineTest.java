package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineTest {
    private final int kittensCount;
    @Mock
    Feline mockedAnimal;
    @Spy
    Feline spyFeline = new Feline();

    public FelineTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return TestData.TEST_DATA_FOR_FELINE_TEST;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void eatMeatReturnListOfPredatorFood() {
        try {
            Feline feline = new Feline();
            List expected = TestData.PREDATOR_FOOD;
            List actual = feline.eatMeat();
            Mockito.when(mockedAnimal.getFood("Хищник")).thenReturn(TestData.PREDATOR_FOOD);
            assertEquals("Не еда хищников!", expected, actual);
        } catch (Exception e) {
            System.out.println("Проверьте параметры теста!");
        }
    }

    @Test
    public void getFamilyReturnFeline() {
        Feline feline = new Feline();
        String expected = "Кошачьи";
        String actual = feline.getFamily();
        assertEquals("Не кошачьи!", expected, actual);
    }

    @Test
    public void getKittensReturnTwoKittens() {
        Feline feline = new Feline();
        int expected = kittensCount;
        int actual = feline.getKittens(kittensCount);
        assertEquals("Не правильное количево катят!", expected, actual);
    }

    @Test
    public void getKittensNoParamInvokeGetKittensWith1() {
        spyFeline.getKittens();
        int expected = 1;
        Mockito.verify(spyFeline, Mockito.times(expected)).getKittens(expected);
    }

}