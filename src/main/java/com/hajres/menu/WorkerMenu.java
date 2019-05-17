package com.hajres.menu;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

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

            switch (selectedOption) {
                case 1:
                    System.out.println("Selected \"Add new worker\"!");
                    break;
                case 2:
                    listAllWorkers();
                    break;
                case 3:
                    System.out.println("Selected \"Find worker by jmbg\"!");
                    break;
                case 4:
                    System.out.println("Selected \"Edit worker\"!");
                    break;
                case 0:
                    System.out.println("Selected \"Back to main menu\"!");
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
        System.out.println("\t4. Edit worker");

        System.out.println("\n\t0. Back to main menu");
    }

    private void listAllWorkers() {
        ArrayList<Worker> workerList = dao.findAll();
        for(Worker w : workerList) {
            System.out.println(w);
        }
        System.out.println("Press ENTER to continue...");
        scanner.next();
    }
}
