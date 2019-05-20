package com.hajres.menu;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

import java.util.ArrayList;

public class CompanyMenu extends Menu {
    private static CompanyMenu menu = null;
    private CompanyDao dao;

    private CompanyMenu() {
        dao = new CompanyDao();
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
                    addCompany();
                    break;
                case 2:
                    printAllCompanies();
                    break;
                case 3:
                    printCompaniesByName();
                    break;
                case 4:
                    printCompanyByCity();
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

    private void addCompany() {
        Company company = getCompanyDate();
        int result= dao.add(company);
        if (result != 0) {
            System.out.println("Company successfully added.");
            System.out.println(company);
        }
    }

    private void printAllCompanies() {
        ArrayList<Company> companyList = dao.findAll();
        for (Company comp: companyList) {
            System.out.println(comp);
        }
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }

    private void printCompaniesByName() {
        System.out.print("Company name or part of it: ");
        String name = scanner.nextLine();

        ArrayList<Company> companyList = dao.findByName(name);
        for (Company comp: companyList) {
            System.out.println(comp);
        }
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }

    private void printCompanyByCity() {
        System.out.println("Enter city: ");
        String city = scanner.nextLine();
        ArrayList<Company> companyList = dao.findByCity(city);
        for (Company comp : companyList) {
            System.out.println(comp);
        }
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }
}
