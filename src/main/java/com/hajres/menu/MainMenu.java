package com.hajres.menu;

public class MainMenu extends Menu {

    private static MainMenu menu = null;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (menu == null) {
            menu = new MainMenu();
        }
        return menu;
    }

    @Override
    public void start() {
        do {
            clearScreen();
            printMenu();
            selectedOption = getIntInput();

            switch (selectedOption) {
                case 1:
                    WorkerMenu.getInstance().start();
                    break;
                case 2:
                    CompanyMenu.getInstance().start();
                    break;
                case 3:
                    AddressMenu.getInstance().start();
                    break;
                case 0:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Please try again!");
            }

        } while (selectedOption != 0);
    }

    @Override
    void printMenu() {
        System.out.println("\n===============================");
        System.out.println("********** Main Menu **********");
        System.out.println("===============================");
        System.out.println("\t1. Worker");
        System.out.println("\t2. Company");
        System.out.println("\t3. Address");

        System.out.println("\n\t0. Exit");
    }
}
