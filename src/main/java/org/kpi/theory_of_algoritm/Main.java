//Including all the side program files
package org.kpi.theory_of_algoritm;
import org.kpi.theory_of_algoritm.ArrayProviders.ArrayProvider;
import org.kpi.theory_of_algoritm.ArrayProviders.ConsoleProvider;
import org.kpi.theory_of_algoritm.ArrayProviders.FileProvider;
import org.kpi.theory_of_algoritm.ArrayProviders.Randomizer;
import org.kpi.theory_of_algoritm.Exception.ArrayProviderException;
import org.kpi.theory_of_algoritm.Exception.MenuException;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        //Developing the interface for our program to make it look better
        String str = "Лабораторна робота 1, Бригада №10";
        System.out.printf("%1$" + (10 + str.length()) + "s", str);
        System.out.println("\n__________________________________________________");
        System.out.printf("%1$" + 30 + "s", "START");

        //Start of the program
        System.out.println("\nThere are 3 options for you to present an array. You can select only one!");
        System.out.println("To generate array elements - print 1");
        System.out.println("To enter the array by yourself - print 2");
        System.out.println("To read from file - print 3 ");
        System.out.print("Enter your type of input: ");
        String choice = consoleScanner.nextLine();
        System.out.println("__________________________________________________");

        try {

            /*The algorithm of getting an array based on a data from the input
            and all the processes occurred in side files after choice was made
             */
            ArrayProvider provider = getArrayProvider(choice);
            List<Float> array = provider.getArray();
            outputArray(array);
            countBiggerThen(array, getValueToCompare());
        } catch (ArrayProviderException | MenuException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayProvider getArrayProvider(String choice) throws MenuException {
        return switch (choice) {

            //Logical response is here: when the user prints a value, we call a class
            case "1" -> new Randomizer(getSize(), getFilePath());
            case "2" -> new ConsoleProvider(getSize());
            case "3" -> new FileProvider(getFilePath());
            default -> throw new MenuException("You have entered the wrong menu option! Choose either 1, 2 or 3.");
        };
    }

    private static void outputArray(List<Float> array) {

        //Output the array
        System.out.println("__________________________________________________");
        System.out.println("Your array: ");
        System.out.println(array);
        System.out.println("__________________________________________________");
    }

    private static float getValueToCompare() throws MenuException {

        /*
        Here we ask our user to give us a certain value to compare
        each element with
         */
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter a value to compare: ");
        try {
            return Float.parseFloat(consoleScanner.nextLine());
        } catch (NumberFormatException e) {
            throw new MenuException("Entered value doesn't match the requirements. Make sure your input is a number.");
        }
    }

    private static void countBiggerThen(List<Float> array, float valueToCompare) {
        long start = System.nanoTime();
        int amount = 0;
        for (Float aFloat : array) {
            if (aFloat > valueToCompare) {

                //Iterating through each element and doing simple tests
                amount++;
            }
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);
        //Showing final results
        System.out.println("__________________________________________________");
        System.out.println("The amount of elements in array greater than the entered number is " + amount + "!");
        System.out.printf("%1$" + 30 + "s", "THE END");

    }

    private static int getSize() throws MenuException {
        Scanner consoleScanner = new Scanner(System.in);

        //Here we ask our user to tell us what is the amount of elements
        System.out.print("Input the amount of elements in array: ");
        String size = consoleScanner.nextLine();

        try {
            if(Integer.parseInt(size) > 0){
                return Integer.parseInt(size);
            } else {
                throw new MenuException("The value must be a positive integer type!");
            }
        } catch (NumberFormatException e) {
            throw new MenuException("The value must be an integer type!");
        }

    }


    private static String getFilePath() {
        Scanner consoleScanner = new Scanner(System.in);

        //Asking to tell the program what is the path to file containing array to work with
        System.out.print("Input the full file path: ");
        return consoleScanner.nextLine();
    }


}


