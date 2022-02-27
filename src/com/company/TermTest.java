package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {
    //if TERMS array changed, make sure to update COEFFICIENTS and/or EXPONENTS array
    private static final String[] TERMS = {"+5", "-3", "+x", "-x",
            "+6x", "-9x", "+x^3", "-x^5", "+x^-2",
            "-x^-7", "+7x^4", "-2x^13", "+25x^-8", "-54x^-17"};
    private static final int[] COEFFICIENTS =   {0, 5, -3, 1, -1, 6, -9, 1, -1, 1, -1, 7, -2, 25, -54};
    private static final int[] EXPONENTS =      {3, 0,  0, 1,  1, 1,  1, 3, 5, -2, -7, 4, 13, -8, -17};
    private static final char[] VARIABLES =     {'a', 'd',  'z', 'b',  'x', 'y',  'h', 'g', 'j', 'u', 'i', 'l', 'p', 'o', 'q'};




    private static int COMPARE_TO_VALUE = 3; //if this is changed, make sure to change array COMPARE_TO_VALUES
    private static final int[] COMPARE_TO_VALUES = {0, 1, 1, 1, 1, 1, 1, 0, -1, 1, 1, -1, -1, 1, 1};
    private static final int ORIGINAL_EQUALS_TEST = 0;
    private static final int ORIGINAL_BEFORE_TEST = -1;
    //private static final int ORIGINAL_AFTER_TEST = 1; //not used

    @Test
    void getCoefficient() {
        for(int i : COEFFICIENTS){
            Term test = new Term(i,1);
            assertEquals(i, test.getCoefficient(),"Coefficient does NOT match expected coefficient.");
        }

    }

    @Test
    void getVariable() {
        Term test = new Term();

        assertEquals('\0', test.getVariable(), "Empty term variable does NOT match expected empty character.");

        test = new Term(1,2);

        assertEquals('x', test.getVariable(), "Default term assignment does NOT match expected variable character." );

        test = new Term(1,'y',2);

        assertEquals('y', test.getVariable(), "Custom term assignment does NOT match expected variable character." );
    }

    @Test
    void getPower() {
        for(int i : EXPONENTS){
            Term test = new Term(1,i);
            assertEquals(i, test.getPower(),"Exponent does NOT match expected exponent.");
        }


    }

    @Test
    void defaultConstructor(){
        Term test = new Term();
        assertEquals(1, test.getCoefficient(),"Coefficient does NOT match expected coefficient.");
        assertEquals(0, test.getPower(),"Exponent does NOT match expected exponent.");
        assertEquals('\0', test.getVariable(), "Empty term variable does NOT match expected empty character.");
    }
    @Test
    void termConstructor(){
        for (int i = 0; i < COEFFICIENTS.length; i++) {
            Term test = new Term(COEFFICIENTS[i], EXPONENTS[i] );
            assertEquals(COEFFICIENTS[i],test.getCoefficient(), "Term coefficient does NOT match expected Coefficient.");
            assertEquals(EXPONENTS[i], test.getPower(),"Exponent does NOT match expected exponent.");
        }
    }

    @Test
    void fullExpressionConstructor(){
        for (int i = 0; i < COEFFICIENTS.length; i++) {
            Term test = new Term(COEFFICIENTS[i],VARIABLES[i] , EXPONENTS[i] );
            assertEquals(COEFFICIENTS[i],test.getCoefficient(), "Term coefficient does NOT match expected Coefficient.");
            assertEquals(EXPONENTS[i], test.getPower(),"Exponent does NOT match expected exponent.");
            assertEquals(VARIABLES[i], test.getVariable(), "Custom term assignment does NOT match expected variable character." );
        }
    }

    @Test
    void stringConstructor(){
        for (int i = 0; i < TERMS.length; i++) {
            Term test = new Term(TERMS[i]);
            assertEquals(TERMS[i].replaceAll("\\+",""), test.toString(), "Resultant constructed term object does NOT match expected term string.");
        }
    }



    @Test
    void setAll() {
        for (int i = 0; i < COEFFICIENTS.length; i++) {
            Term test = new Term();
            test.setAll(COEFFICIENTS[i], VARIABLES[i] ,EXPONENTS[i] );
            assertEquals(COEFFICIENTS[i],test.getCoefficient(), "Term coefficient does NOT match expected Coefficient.");

            assertEquals(COEFFICIENTS[i],test.getCoefficient(), "Term coefficient does NOT match expected Coefficient.");

            assertEquals(EXPONENTS[i], test.getPower(),"Exponent does NOT match expected exponent.");
        }

    }

    @Test
    void setCoefficient() {
        for(int i : COEFFICIENTS){
            Term test = new Term();
            test.setCoefficient(i);
            assertEquals(i, test.getCoefficient(), "Set coefficient does NOT match expected coefficient.");
        }

    }

    @Test
    void setPower() {
        for(int i : EXPONENTS){
            Term test = new Term();
            test.setPower(i);
            assertEquals(i, test.getPower(), "Set exponent does NOT match expected exponent.");
        }
    }

    @Test
    void setVariable() {
        for(char i : VARIABLES){
            Term test = new Term();
            test.setVariable(i);
            assertEquals(i, test.getVariable(), "Set variable does NOT match expected variable.");
        }
    }

    @Test
    void testToString() {
        for (int i = 0; i < TERMS.length; i++) {
            Term test = new Term(TERMS[i]);
            assertEquals(TERMS[i].replaceAll("\\+",""), test.toString(), "Resultant constructed term object does NOT match expected term string.");
        }
    }

    @Test
    void testEquals() {
        Term prev = null;
        for (int i = 0; i < TERMS.length; i++) {
            Term test = new Term(TERMS[i]);
            assertTrue(test.equals(test), "Resultant of comparing to self does NOT equal to true.");
            if(prev !=null)
                 assertTrue(!test.equals(prev), "Resultant of comparing self to non-equal object does NOT equal to false.");
            prev = test;
        }

    }

    @Test
    void compareTo() {

        Term original = new Term(1, COMPARE_TO_VALUE), test;
        int e, compareResult;

        for(int i = 0; i < EXPONENTS.length; i++) {
            e = EXPONENTS[i];

            test = new Term(1, e);
            compareResult = original.compareTo(test);

            if( COMPARE_TO_VALUES[i] == ORIGINAL_EQUALS_TEST) {
                assertEquals(0, compareResult,"Expected and actual compareTo result (original exponent EQUALS test exponent) DON'T match");
            }
            else if ( COMPARE_TO_VALUES[i] == ORIGINAL_BEFORE_TEST) {
                assertTrue(compareResult < 0,"Expected and actual compareTo result (original exponent COMES BEFORE test exponent) DON'T match");
            } else {
                assertTrue(compareResult > 0,"Expected and actual compareTo result (original exponent COMES AFTER test exponent) DON'T match");
            }

        }
    }
}