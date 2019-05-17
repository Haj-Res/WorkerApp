package com.hajres.menu;

import java.util.Scanner;

abstract class Menu {

    protected Scanner scanner = new Scanner(System.in);
    protected int selectedOption;
    abstract void printMenu();

    abstract public void start();

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
