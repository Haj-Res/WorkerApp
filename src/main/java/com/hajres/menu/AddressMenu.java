package com.hajres.menu;

import com.hajres.domain.model.Address;

public class AddressMenu extends Menu {
    private static AddressMenu menu = null;

    private AddressMenu() {
    }

    public static AddressMenu getInstance() {
        if (menu == null) {
            menu = new AddressMenu();
        }
        return menu ;
    }

    @Override
    void printMenu() {
        System.out.println("\n==============================");
        System.out.println("******** Address Menu ********");
        System.out.println("==============================");
        System.out.println("\t1. Add new address");
        System.out.println("\t2. List addresses");
        System.out.println("\t3. Search address by city");

        System.out.println("\n\t0. Back to main menu");
    }

    @Override
    public void start() {
        do {
            clearScreen();
            printMenu();
            selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 1:
                    System.out.println("Add address");
                    break;
                case 2:
                    System.out.println("Print all addresses");
                    break;
                case 3:
                    System.out.println("Print by city");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Try again");
            }

        } while (selectedOption != 0);
    }

    public Address getAddressData() {
        Address address = new Address();
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter address");
            String city = "";
            System.out.print("City: ");
            while (city.equals("")) {
                city = scanner.nextLine();
            }
            System.out.print("Street: ");
            String street = "";
            while (street.equals("")) {
                street = scanner.nextLine();
            }
            System.out.print("Number: ");
            String number = "";
            while (number.equals("")) {
                number = scanner.nextLine();
            }
            address.setCity(city);
            address.setStreet(street);
            address.setNumber(number);

            System.out.println(address);
            valid = getConfirmation("Is the entered data correct?");
        }
        return address;
    }
}
