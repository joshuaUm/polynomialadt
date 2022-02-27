package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    //TERMS + POLYNOMIALS with unique terms (no duplicate terms = terms with same exponents)
    private static final Term[] UNIQUE_TERMS1 = {   new Term(5, 7),
            new Term(5, 9),
            new Term(-2, 2),
            new Term(-4, -3)};
    private static final String[] UNIQUE_POLY1 = {  "5x^7",
            "5x^9 + 5x^7",
            "5x^9 + 5x^7 - 2x^2",
            "5x^9 + 5x^7 - 2x^2 - 4x^-3"};

    private static final String[] UNIQUE_ADD_TEST_POLY1 = {
            "5x^7 + 6x^2 - 2x", "6x^8 + 3x^7 + 4x",
            "6x^8 + 8x^7 + 6x^2 + 2x" // expected result
    };



    //TERMS + POLYNOMIALS with unique terms (no duplicate terms = terms with same exponents)
    private static final Term[] DUPLICATE_TERMS = {
            new Term(-12, 11),
            new Term(3, 13),
            new Term(-1, 4),
            new Term(9, 6),
            new Term(52, 8),
            new Term(-88, 10),
            new Term(11, 12),
            new Term(7, 2),
            new Term(-1, -1),
            new Term(4, 1),
            new Term(12, 0),
            new Term(100, 5),
            new Term(100, 10),  //duplicate
            new Term(-10, 0),   //duplicate
            new Term(-1, -1),   //duplicate
            new Term(-2, 13),   //duplicate
            new Term(-50, 8)    //duplicate
    };

    @Test
    void stringConstructor(){
        for (String s : UNIQUE_POLY1) {
            Polynomial test = new Polynomial(s);
            assertEquals(s, test.toString(), "toString does NOT match expected string.");
        }
    }


    @Test
    void setTerms(){
        ArrayList<Term> testList1 = new ArrayList<Term>();
        Polynomial test = new Polynomial();

        for(Term t : UNIQUE_TERMS1){
            testList1.add(t);
        }

        test.setTerms(testList1);

        assertEquals(testList1, test.getTerms(), "List reference in Polynomial object does NOT match expected list reference.");

        for(Term t : UNIQUE_TERMS1){
            assertTrue(test.getTerms().contains(t), "List does NOT contain expected term object.");
        }
    }

    @Test
    void getTerms() {

        Polynomial test = new Polynomial();

        ArrayList<Term> termList = test.getTerms();

        assertTrue(termList != null , "Term list does NOT exist.");
        assertTrue(termList.isEmpty(), "Term list is NOT empty.");

        for(Term t : UNIQUE_TERMS1){
            termList.add(t);
        }

        assertEquals(UNIQUE_TERMS1.length,termList.size(), "Resultant term list length does NOT match expected term list length.");


    }

    @Test
    void simplifyPolynomial() {
        Polynomial test = new Polynomial();

        for(Term t : DUPLICATE_TERMS)
            test.addTerm(t);

        test.simplifyPolynomial();

        assertEquals(DUPLICATE_TERMS.length -5 , test.getTerms().size(),"Length of objects in term list do NOT match expected duplicate term length.");


    }

    @Test
    void addPolynomial() {
        Polynomial test1 = new Polynomial(UNIQUE_ADD_TEST_POLY1[0]);
        Polynomial test2 = new Polynomial(UNIQUE_ADD_TEST_POLY1[1]);
        Polynomial expected = new Polynomial(UNIQUE_ADD_TEST_POLY1[2]);

        assertEquals(expected.toString(),test1.addPolynomial(test2).toString(),"Resultant polynomial did NOT match expected polynomial.");
    }

    @Test
    void addTerm() {
        Polynomial test = new Polynomial();

        for(Term t : UNIQUE_TERMS1)
            test.addTerm(t);

        assertEquals(UNIQUE_TERMS1.length, test.getTerms().size(),"Length of objects in term list do NOT match expected unique term length");
    }

    @Test
    void testToString() {
        for (String s : UNIQUE_POLY1) {
            Polynomial test = new Polynomial(s);
            assertEquals(s, test.toString(), "toString does NOT match expected string.");
        }
    }
}