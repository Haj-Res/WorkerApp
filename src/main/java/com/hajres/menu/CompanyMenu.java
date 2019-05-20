package com.hajres.menu;

import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

public class CompanyMenu extends Menu {
    private static CompanyMenu menu = null;

    private CompanyMenu() {
    }

    public static CompanyMenu getInstance() {
        if (menu == null) {
            menu = new CompanyMenu();
        }
        return menu;
    }

    @Override
    void printMenu() {
        System.out.println("\n==============================");
        System.out.println("******** Company Menu ********");
        System.out.println("==============================");
        System.out.println("\t1. Add new company");
        System.out.println("\t2. List companies");
        System.out.println("\t3. Search company by name");
        System.out.println("\t4. Search company by city");

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
                    System.out.println("Add company");
                    break;
                case 2:
                    System.out.println("Print all companies");
                    break;
                case 3:
                    System.out.println("Print by name");
                    break;
                case 4:
                    System.out.println("Print by city");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Try again");
            }

        } while (selectedOption != 0);
    }

    public Company getCompanyDate() {
        Company company = new Company();
        System.out.println("Enter company");
        boolean valid = false;
        String name = "";
        Address address;
        while (!valid) {
            System.out.println("Enter company data");
            while (name.equals("")) {
                System.out.print("Company name: ");
                name = scanner.nextLine();
            }
            company.setName(name);
            address = AddressMenu.getInstance().getAddressData();
            company.setAddress(address);
            System.out.println(company);
            valid = getConfirmation("Is the entered data correct?");
        }
        return company;
    }
}
