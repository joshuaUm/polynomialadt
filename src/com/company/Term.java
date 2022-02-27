package com.company;

import java.util.Objects;

public class Term implements Comparable<Term> {

    private int coefficient;
    private char variable = '\0';
    private int power;


    public Term(){
        setAll(1,'\0',0);
    }

    public Term(Term t){ setAll(t.getCoefficient(), t.getVariable(), t.getPower());}

    public Term(int coefficient, int power){ setAll(coefficient, 'x', power);}

    public Term(int coefficient, char variable, int power) {
        setAll(coefficient, variable, power);
    }

    public Term(String s) throws IllegalArgumentException {

        /*
         Check if expression is a valid term. (See Polynomial string constructor for regex breakdown.)
         */
        if(!s.matches("(\\-?\\+?\\d+\\w\\^-?\\d+)|(\\-?\\+?\\w\\^-?\\d+)|(\\-?\\+?\\d?\\w)")){
            throw new IllegalArgumentException("Invalid Term given. String given : " + s );
        }


        String letterString = s.replaceAll("[\\W\\d\\+]", "");


        // Check for variable in term.
        if(letterString.matches("[A-Za-z]")){

            String[] substring = s.split("[A-Za-z]");
            // Store coefficient string as integer before variable character, otherwise store as 1.
            coefficient = (substring[0].matches("\\+\\d+|\\-\\d+|\\d+") ? Integer.parseInt(substring[0]) : Integer.parseInt(substring[0] + 1));

            variable = letterString.charAt(0);

            if(substring.length > 1)
                power = Integer.parseInt(substring[1].substring(1));
            else
                power = 1;

        }
        else
            coefficient = Integer.parseInt(s);

    }




    public int getCoefficient() {
        return coefficient;
    }

    public char getVariable() {
        return variable;
    }

    public int getPower() {
        return power;
    }

    public void setAll(int coefficient, char variable, int power){
        setCoefficient(coefficient);
        setPower(power);
        setVariable(variable);
    }


    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setVariable(char variable) {
        this.variable = variable;
    }

    @Override
    public String toString(){
        return ((coefficient == 1 ? "" : coefficient == -1 ? "-" : coefficient )+ "" + (variable == '\0' ?  "" : Character.toString(variable)) + (power == 0 | power == 1 ? "" : "^" + power));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return coefficient == term.coefficient && variable == term.variable && power == term.power;
    }

    @Override
    public int compareTo(Term term) {
            return getPower() - term.getPower();
    }
}
