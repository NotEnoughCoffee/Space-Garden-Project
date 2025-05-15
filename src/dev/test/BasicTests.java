package dev.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasicTests {
    //Basic Unit Test Class//





    //Example Test Setup//
    public int exampleCompare (int x, int y) {
        return x > y ? 1 : - 1;
    }
    @Test
    @DisplayName("Example Test: Compare Value X is greater than Value Y")
    public void exampleTest() {
        int value = exampleCompare(2,1);
        Assertions.assertEquals(1, value);
    }

}
