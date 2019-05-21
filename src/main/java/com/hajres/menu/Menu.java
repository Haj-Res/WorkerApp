package com.hajres.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Menu {
    protected final static int LINE_LIMIT = 15;

    protected Scanner scanner = new Scanner(System.in);
    protected int selectedOption;

    abstract void printMenu();

    abstract public void start();

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected boolean getConfirmation(String question) {
        String choice;
        while (true) {
            System.out.println(question + " (Y/N)");
            choice = scanner.nextLine();
            if (choice.length() < 1) {
                continue;
            }
            choice = choice.toLowerCase();
            char c = choice.charAt(0);
            if (c == 'y') {
                return true;
            } else if (c == 'n') {
                return false;
            }
        }
    }

    protected void printArray(List list) {
        int counter = 1;
        for(Object obj: list) {
            System.out.print(counter + ".\t");
            System.out.println(obj);
            if (counter % LINE_LIMIT == 0) {
            System.out.println("Press ENTER to view next " + LINE_LIMIT);
                scanner.nextLine();
            }
            counter++;
        }
    }

    protected int getIntInput() {
        String stringInput;
        int result = 0;
        boolean isNumeric = false;
        while (!isNumeric) {
            stringInput = scanner.nextLine();
            try {
                result = Integer.parseInt(stringInput);
                isNumeric = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        return result;
    }
}
