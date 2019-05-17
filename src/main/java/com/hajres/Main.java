package com.hajres;

import com.hajres.menu.MainMenu;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static int userInput;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        MainMenu.getInstance().start();
    }
}
