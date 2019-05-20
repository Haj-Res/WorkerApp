package com.hajres.menu;

import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

public class CompanyMenu extends Menu {
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
        AddressMenu addressMenu = new AddressMenu();
        Address address;
        while (!valid) {
            System.out.println("Enter company data");
            while (name.equals("")) {
                System.out.print("Company name: ");
                name = scanner.nextLine();
            }
            company.setName(name);
            address = addressMenu.getAddressData();
            company.setAddress(address);
            System.out.println(company);
            valid = getConfirmation("Is the entered data correct?");
        }
        return company;
    }
}
