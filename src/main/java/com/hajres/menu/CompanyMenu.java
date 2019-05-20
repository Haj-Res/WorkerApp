package com.hajres.menu;

import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

public class CompanyMenu extends Menu {
    private static CompanyMenu menu = null;

    private CompanyMenu () {
    }

    public static CompanyMenu getInstance() {
        if (menu == null) {
            menu = new CompanyMenu();
        }
        return  menu;
    }
    @Override
    void printMenu() {

    }

    @Override
    public void start() {

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
