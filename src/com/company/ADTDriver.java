package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ADTDriver {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Polynomial firstPoly = null, secondPoly = null;

        firstPoly = addPolynomial(keyboard);

        System.out.println("1st Polynomial entered :" +  firstPoly);

        secondPoly = addPolynomial(keyboard);

        System.out.println("2nd Polynomial entered :" +  secondPoly);


        int choice = 0;

        while(choice != -1){
            System.out.println( "\n1st polynomial : " + firstPoly + "\n2nd polynomial : " + secondPoly + "\nChoose action (Enter -1 to exit program): \n 1. Edit first polynomial\n 2. Edit second polynomial\n 3. Add both polynomials");
            choice = keyboard.nextInt();
            keyboard.nextLine();

            switch(choice){

                case 1:
                {
                    Polynomial newPoly = editPolynomial(keyboard, firstPoly);
                    if(newPoly !=null) firstPoly = newPoly;
                }
                    break;
                case 2:
                {
                    Polynomial newPoly = editPolynomial(keyboard, secondPoly);
                    if(newPoly !=null) secondPoly = newPoly;
                }
                    break;
                case 3:
                    try{
                        System.out.println( "(" + firstPoly + ") + (" + secondPoly + ") = " +  firstPoly.addPolynomial(secondPoly));
                    } catch (IllegalArgumentException e){
                        System.out.println("Error: " + e);
                    }

                case -1:
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }

        }



        // Close input stream.
        keyboard.close();


    }

    public static Polynomial editPolynomial(Scanner keyboard, Polynomial polynomial) {

        int choice = 0;
        while (choice != -1) {
            System.out.println("Selected polynomial : " + polynomial + "\nChoose action (Enter -1 to cancel):\n 1. Modify Terms\n 2. Delete Terms\n 3. Add Term");

            choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case -1:
                    break;
                case 1:
                    editTerm(keyboard, polynomial, false);
                    break;
                case 2:
                    editTerm(keyboard, polynomial, true);
                    break;
                case 3:
                    polynomial.addTerm(createTerm(keyboard));
                    System.out.print(polynomial + " -> ");
                    polynomial.simplifyPolynomial();
                    System.out.println(polynomial);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }


        }

        return null;
    }


    public static void editTerm(Scanner keyboard, Polynomial polynomial, boolean doDelete){
        int choice = 0 ;
        ArrayList<Term> termList = polynomial.getTerms();

        if(termList.size() == 0){
            System.out.println("Error : No terms in polynomial.");
            return;
        }

        while (termList.size() > 0){
            System.out.println("\nSelected polynomial : " + polynomial + "\nSelect term to " + (doDelete ? "delete (Enter -1 to cancel): " : "modify (Enter -1 to cancel): "));
            for (int i = 0; i < termList.size(); i++) {
                System.out.println((i + 1) + ". " + termList.get(i));
            }

            choice = keyboard.nextInt();
            keyboard.nextLine();

            if(choice == -1) break;

            if(--choice >= 0 && choice < termList.size()){

                if(doDelete)
                    System.out.println("Term deleted : " + termList.remove(choice));
                else
                {
                    Term newTerm = createTerm(keyboard);
                    System.out.println("New Term replacing term[" + choice + "] : " + newTerm);
                    termList.remove(choice);
                    polynomial.addTerm(newTerm);

                    System.out.print(polynomial + " -> " );
                    polynomial.simplifyPolynomial();
                    System.out.println(polynomial);
                }
            }
        }


    }

    public static Term createTerm(Scanner keyboard){
        Term newTerm = null;
        while(newTerm == null) {
            System.out.print("Enter term : ");
            String termString = keyboard.nextLine();
            try {
                newTerm = new Term(termString);
            } catch (IllegalArgumentException e) {
                System.out.println("Error : " + e);
            }
        }

        return newTerm;
    }


    public static Polynomial addPolynomial(Scanner keyboard){
        Polynomial polynomial = null;
        while(polynomial == null){
            System.out.print("Enter polynomial : ");
            String polyString = keyboard.nextLine();

            try{
                polynomial = new Polynomial(polyString);
            }catch(IllegalArgumentException e){
                System.out.println("Error : " + e);
            }

        }


        return polynomial;



    }


}
