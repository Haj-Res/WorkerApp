package com.hajres.menu;

import com.hajres.domain.dao.AddressDao;
import com.hajres.domain.model.Address;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class AddressMenu extends Menu {
    private static AddressMenu menu = null;
    private AddressDao dao;

    private AddressMenu() {
        dao = new AddressDao();
    }

    public static AddressMenu getInstance() {
        if (menu == null) {
            menu = new AddressMenu();
        }
        return menu;
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
                    addAddress();
                    break;
                case 2:
                    printAllAddresses();
                    break;
                case 3:
                    printAddressByCity();
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

    private void printArray(ArrayList<Address> addressList) {
        int counter = 1;
        for (Object address: addressList) {
            System.out.print(counter + ".\t");
            System.out.println(address);
            if (counter % 10 == 0) {
                System.out.println("Press ENTER to view next 10");
                scanner.nextLine();
            }
            counter++;
        }
    }

    private void addAddress() {
        Address address = getAddressData();
        int result = dao.add(address);
        if (result != 0) {
            System.out.println("Address successfully added.");
            System.out.println(address);
        }
    }

    private void printAllAddresses() {
        ArrayList<Address> addressList = new ArrayList<>();
        addressList = dao.findAll();
        printArray(addressList);
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }

    private void printAddressByCity() {
        System.out.print("City or part of the name: ");
        String city = scanner.nextLine();
        ArrayList<Address> addressList = dao.findByCity(city);
        printArray(addressList);
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }
}
