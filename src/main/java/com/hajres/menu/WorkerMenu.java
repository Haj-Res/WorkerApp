package com.hajres.menu;

import com.hajres.domain.dao.AddressDao;
import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import com.hajres.domain.model.Worker;

import java.io.IOException;
import java.lang.invoke.LambdaConversionException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class WorkerMenu extends Menu {

    private static WorkerMenu menu = null;
    private WorkerDao dao;

    private WorkerMenu() {
        dao = new WorkerDao();
    }

    public static WorkerMenu getInstance() {
        if (menu == null) {
            menu = new WorkerMenu();
        }
        return menu;
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
                    addWorker();
                    break;
                case 2:
                    printAllWorker();
                    break;
                case 3:
                    printWorkerByJmbg();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Try again");
            }

        } while (selectedOption != 0);
    }

    @Override
    void printMenu() {
        System.out.println("\n==============================");
        System.out.println("*********** Worker ***********");
        System.out.println("==============================");
        System.out.println("\t1. Add new worker");
        System.out.println("\t2. List workers");
        System.out.println("\t3. Find worker by jmbg");

        System.out.println("\n\t0. Back to main menu");
    }

    private void addWorker() {
        Worker worker = getWorkerData();
        dao.add(worker);

        System.out.println();
    }

    private void printAllWorker() {
        ArrayList<Worker> workerList = dao.findAll();
        for (Worker w : workerList) {
            System.out.println(w);
        }
        System.out.println("Press ENTER to continue . . .");
        scanner.nextLine();
    }

    private void printWorkerByJmbg() {
        String jmbg = "";
        Worker worker = null;
        while (true) {
            System.out.print("Enter worker jmbg: ");
            jmbg = scanner.next();
            scanner.nextLine();
            if (jmbg.length() > 13) {
                System.out.println("Jmbg can't be longer than 13 characters. Try again.");
            } else {
                worker = dao.findById(jmbg);
                break;
            }
        }
        if (worker.getJmbg() == null) {
            System.out.println("No workers found.");
        } else {
            System.out.println(worker);
            boolean edit = getConfirmation("Edit worker data?");
            if (edit) {
                updateWorker(worker);
            }
        }
    }

    private Worker getWorkerData() {
        Worker worker = new Worker();
        boolean validWorker = false;

        while (!validWorker) {
            System.out.println("Enter worker data");
            boolean valid = false;
            String jmbg = null;
            while (!valid) {
                System.out.print("JMBG (13 character): ");
                jmbg = scanner.next();
                scanner.nextLine();
                if (jmbg.length() == 13) {
                    valid = true;
                }
            }
            worker.setJmbg(jmbg);
            System.out.print("First name: ");
            worker.setFirstName(scanner.next());
            scanner.nextLine();
            System.out.print("Last name: ");
            worker.setLastName(scanner.next());
            scanner.nextLine();


            String pattern = "d/M/yyyy";
            LocalDate birthDate = null;
            valid = false;
            setWorkerBirthDate(worker, pattern, birthDate, valid);
            Address address = AddressMenu.getInstance().getAddressData();
            worker.setAddress(address);
            boolean employed = getConfirmation("Is worker employed?");
            if (employed) {
                worker.setCompany(CompanyMenu.getInstance().getCompanyDate());
            } else {
                worker.setCompany(null);
            }
            System.out.println("Entered worker: ");
            System.out.println(worker);
            validWorker = getConfirmation("Is the entered data correct?");
        }

        return worker;
    }

    private void updateWorker(Worker worker) {
        boolean delete = getConfirmation("Would you like to delete the worker?");
        if (delete) {
            System.out.println("\n==============================");
            System.out.println("******* Deleting worker ******");
            System.out.println("==============================");
            while (true) {
                delete = getConfirmation("Are you sure you want to delete the worker? This process is irreversible?");
                if (delete) {
                    System.out.println(worker);
                    System.out.print("Enter worker JMBG to confirm: ");
                    String jmbg = scanner.nextLine();
                    if (worker.getJmbg().equals(jmbg)) {
                        dao.delete(worker.getJmbg());
                        break;
                    } else {
                        System.out.println("Incorrect jmbg!");
                    }
                } else {
                    break;
                }
            }
        } else {
            boolean change;
            System.out.println("\n==============================");
            System.out.println("******* Editing worker *******");
            System.out.println("==============================");
            boolean valid = false;
            String oldJmbg = worker.getJmbg();
            while (!valid) {
                change = getConfirmation("Change JMBG?");
                if (change) {
                    String jmbg = "";
                    do {
                        System.out.print("JMBG: ");
                        jmbg = scanner.nextLine();
                    } while (jmbg.length() > 13);
                    worker.setJmbg(jmbg);
                }

                change = getConfirmation("Change first name?");
                if (change) {
                    String firstName = "";
                    do {
                        System.out.print("First name: ");
                        firstName = scanner.nextLine();
                    } while (firstName.equals(""));
                    worker.setFirstName(firstName);
                }

                change = getConfirmation("Change last name?");
                if (change) {
                    String lastName = "";
                    do {
                        System.out.print("Last name: ");
                        lastName = scanner.nextLine();
                    } while (lastName.equals(""));
                    worker.setLastName(lastName);
                }

                change = getConfirmation("Change birth date?");
                if (change) {
                    String pattern = "d/M/yyyy";
                    LocalDate birthDate = null;
                    boolean validDate = false;
                    setWorkerBirthDate(worker, pattern, birthDate, validDate);
                }

                change = getConfirmation("Change address?");
                if (change) {
                    Address address = AddressMenu.getInstance().getAddressData();
                    worker.setAddress(address);
                }

                change = getConfirmation("Change employment?");
                if (change) {
                    change = getConfirmation("Is worker employed?");
                    if (change) {
                        Company company = CompanyMenu.getInstance().getCompanyDate();
                        worker.setCompany(company);
                    } else {
                        worker.setCompany(null);
                    }
                }

                System.out.println(worker);
                valid = getConfirmation("Is the new data correct?");
            }

            dao.update(worker, oldJmbg);
        }
    }

    private void setWorkerBirthDate(Worker worker, String pattern, LocalDate birthDate, boolean valid) {
        while (!valid) {
            System.out.print("Birth date (" + pattern + "): ");
            DateTimeFormatter formatter = null;
            formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                String inputDate = scanner.nextLine();
                birthDate = LocalDate.parse(inputDate, formatter);
                System.out.println("Enterd data: " + birthDate);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect date, try again.");
            }
            worker.setBirthDate(birthDate);
        }
    }
}
