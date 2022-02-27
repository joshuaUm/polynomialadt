package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private ArrayList<Term> terms = new ArrayList<Term>();

    public Polynomial(){}

    public Polynomial(String s) throws IllegalArgumentException{
        // Check if expression has the following to be a valid polynomial string:
        // - May have a +/- sign predating the expression
        // - has zero or more numbers predating a non-digit character
        // - accompanied with a '^' symbol with a possible negative character preceding 1 or more digits.
        // - OR :
        // -    May have a +/- sign predating the expression.
        // -    accompanied by zero or more digits which predates any non-digit character.
        String regex = "(\\-?\\+?\\d+\\w\\^-?\\d+)|(\\-?\\+?\\w\\^-?\\d+)|(\\-?\\+?\\d?\\w)";
        String qString = s.replaceAll(" ", "");
        if(qString.replaceAll(regex, "").length() > 0){
            throw new IllegalArgumentException("Invalid polynomial given.");
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(qString);

        while(matcher.find()){
            addTerm(new Term(matcher.group()));
        }

        simplifyPolynomial();



    }
    public ArrayList<Term> getTerms(){
        return terms;
    }
    public void setTerms(ArrayList<Term> terms){ this.terms = terms;}

    public void simplifyPolynomial(){
        int removed = 0;
        for(int i = 0 ; i < getTerms().size() - removed ; i++)
        {
            Term t = getTerms().get(i);
            for (int j = 0; j < getTerms().size() - removed; j++) {
                Term indexedTerm = getTerms().get(j);
                if(i == j) continue;
                if( t.getVariable() == indexedTerm.getVariable() && t.compareTo(indexedTerm) == 0){
                    t.setCoefficient(t.getCoefficient() + indexedTerm.getCoefficient());
                    getTerms().remove(j);
                    break;
                }
            }
        }


    }

    public Polynomial addPolynomial(Polynomial polynomial) throws IllegalArgumentException{

        if(getTerms().size() == 0 || polynomial.getTerms().size() == 0){
            throw new IllegalArgumentException("One of the polynomials given is empty.");
        }

        Polynomial sumPolynomial = new Polynomial();

        ArrayList<Term> addedTerms = sumPolynomial.getTerms();

        for(Term t : getTerms()){
            addedTerms.add(new Term(t));
        }

        for(Term t : polynomial.getTerms()){
            Term termSum = null;

            for(Term thisT : addedTerms){
                if(thisT.compareTo(t) == 0){
                    termSum = thisT;
                    thisT.setCoefficient(thisT.getCoefficient() + t.getCoefficient());
                    break;
                }
            }
            if(termSum == null){
                sumPolynomial.addTerm(t);
            }
        }
        return sumPolynomial;
    }



    public void addTerm(Term t){
        terms.add(t);
        if(terms.size() < 2 ) return;
        else{
            int smallest = 0, smallestIndex = 0;
            for (int i = 0; i < terms.size(); i++) {
                for (int j = 0; j < terms.size() - i - 1; j++) {
                    if (terms.get(j).compareTo(terms.get(j + 1)) < 0) {
                        Term temp = terms.get(j);
                        terms.set(j, terms.get(j + 1));
                        terms.set(j + 1, temp);
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0 ; i < terms.size(); i++){
            Term t = terms.get(i);
            if (i > 0)
                str += (t.getCoefficient() > 0  ? "+ " : "- ");
            str +=  t.toString()    .replaceAll("^\\+|^\\-", "") + " " ;
        }

        if(str.length() > 0)
            str = str.substring(0,str.length()-1);

        return str;
    }




}
