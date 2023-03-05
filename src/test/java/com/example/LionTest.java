package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LionTest {
    private final String sex;
    private final boolean doesHaveMane;
    @Mock
    Feline mockedFeline;

    public LionTest(String sex, boolean doesHaveMane) {
        this.sex = sex;
        this.doesHaveMane = doesHaveMane;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return TestData.TEST_DATA_FOR_LION_TEST;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getKittensReturnKittensCount() throws Exception {
        Lion lion = new Lion("Самец", mockedFeline);
        Mockito.when(mockedFeline.getKittens()).thenReturn(1);
        int expected = 1;
        int actual = lion.getKittens();
        assertEquals("Метод getKittens() вызван не с аругментом 1!", expected, actual);
    }

    @Test
    public void doesHaveMane() throws Exception {
        Lion lion = new Lion(sex, mockedFeline);
        boolean expected = doesHaveMane;
        boolean actual = lion.doesHaveMane();
        assertEquals("Не верное утверждение о наличии гривы!", expected, actual);
    }

    @Test
    public void doesHaveManeNotDefined() throws Exception {
        try {
            Lion lion = new Lion("Квир", mockedFeline);
            Assert.fail("Ожидаем Exception");
        } catch (Exception e) {
            String expected = "Используйте допустимые значения пола животного - самей или самка";
            String actual = e.getMessage();
            assertEquals("Не верное сообщение", expected, actual);
        }
    }

    @Test
    public void getFoodReturnListOfPredatorFood() throws Exception {
        Lion lion = new Lion("Самка", mockedFeline);
        Mockito.when(mockedFeline.getFood("Хищник")).thenReturn(TestData.PREDATOR_FOOD);
        List expected = TestData.PREDATOR_FOOD;
        List actual = lion.getFood();
        assertEquals("Не еда хищников!", expected, actual);
    }

}