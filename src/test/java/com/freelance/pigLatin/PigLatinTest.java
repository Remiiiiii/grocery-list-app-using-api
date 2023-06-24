package com.freelance.pigLatin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PigLatinTest {

    // PigLatin pigLatin = new PigLatin();
    // creating a spy object
    PigLatin pigLatin = Mockito.spy(PigLatin.class);

    @BeforeAll
    public static void beforeTestSuite() {
        System.out.println("This runs before test suite");
    }

    @BeforeEach
    public void beforeEachTestCase() {
        System.out.println("This runs before each individual test case");
    }

    @AfterAll
    public static void afterTestSuite() {
        System.out.println("This runs after test sutie");
    }

    @AfterEach
    public void afterEachTestCase() {
        System.out.println("Runs after each individual test case");
    }

    @Test
    public void convertWord() {
        // arrange (setup)
        String wordToTest = "Revature";
        String expectedOutput = "evatureRay";

        // act (call the method that we're going to try to test)
        String actualOutput = pigLatin.convertWord(wordToTest);

        // assert (checks go here)
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isSentenceValidPositive() {
        // arrange (setup)
        String sentenceToTest = "Hi My name is Remy";
        // Boolean expectedOutput = true;

        // act (call the method that we're going to try to test)
        Boolean actualOutput = pigLatin.isSentenceValid(sentenceToTest);

        // assert (checks go here)
        // Assertions.assertEquals(expectedOutput, actualOutput);
        Assertions.assertTrue(actualOutput);
    }

    @Test
    public void isSentenceValidNegative() {
        // arrange (setup)
        String sentenceToTest = "Hi! My name is Remy";
        // Boolean expectedOutput = true;

        // act (call the method that we're going to try to test)
        Boolean actualOutput = pigLatin.isSentenceValid(sentenceToTest);

        // assert (checks go here)
        // Assertions.assertEquals(expectedOutput, actualOutput);
        Assertions.assertFalse(actualOutput);
    }

    @Test
    public void encodeValidInput() {
        // arrange (setup)
        String sentenceToTest = "Hi my name is Remy";
        String expectedOutput = "iHay ymay amenay siay emyRay";
        Mockito.when(pigLatin.isSentenceValid(sentenceToTest)).thenReturn(true);
        Mockito.when(pigLatin.convertWord("Remy")).thenReturn("emyRay");
        Mockito.when(pigLatin.convertWord("Hi")).thenReturn("iHay");
        Mockito.when(pigLatin.convertWord("my")).thenReturn("ymay");
        Mockito.when(pigLatin.convertWord("name")).thenReturn("amenay");
        Mockito.when(pigLatin.convertWord("is")).thenReturn("siay");

        // act (call the method that we're going to try to test)
        String actualOutput = pigLatin.encode(sentenceToTest);

        // assert (checks go here)
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
