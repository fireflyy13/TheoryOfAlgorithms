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
        String str = "Лабораторна робота 1, Бригада №10";
        System.out.printf("%1$" + (10 + str.length()) + "s", str);
        System.out.println("\n__________________________________________________");
        System.out.printf("%1$" + 30 + "s", "START");
        System.out.println("\nThere are 3 options for you to present an array. You can select only one!");
        System.out.println("To generate array elements - print 1");
        System.out.println("To enter the array by yourself - print 2");
        System.out.println("To read from file - print 3 ");
        System.out.print("Enter your type of input: ");
        String choice = consoleScanner.nextLine();
        System.out.println("__________________________________________________");

        try {
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
            case "1" -> new Randomizer(getSize());
            case "2" -> new ConsoleProvider(getSize());
            case "3" -> new FileProvider(getFilePath());
            default -> throw new MenuException("You have entered the wrong menu option! Choose either 1, 2 or 3.");
        };
    }

    private static void outputArray(List<Float> array) {
        System.out.println("Your array: ");
        System.out.println(array);
    }

    private static float getValueToCompare() throws MenuException {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter a value to compare: ");
        try {
            return Float.parseFloat(consoleScanner.nextLine());
        } catch (NumberFormatException e) {
            throw new MenuException("Entered value doesn't match the requirements. Make sure your input is a number.");
        }
    }

    private static void countBiggerThen(List<Float> array, float valueToCompare) {
        int amount = 0;
        for (Float aFloat : array) {
            if (aFloat > valueToCompare) {
                amount++;
            }
        }
        System.out.println("The amount of elements in array greater than the entered number is " + amount + "!");
        System.out.printf("%1$" + 30 + "s", "THE END");

    }

    private static int getSize() throws MenuException {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Input the amount of elements in array: ");
        String amount = consoleScanner.nextLine();

        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new MenuException("The value must be an integer type!");
        }

    }

    private static String getFilePath() {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Input file path (for example C:\\Users\\Anna\\Desktop\\Array.txt): ");
        return consoleScanner.nextLine();
    }

}


