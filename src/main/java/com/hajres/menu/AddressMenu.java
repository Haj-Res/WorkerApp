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

    }

    @Override
    public void start() {

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
