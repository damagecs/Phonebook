/*
* Phone Book app
* Type 1 to search by phone number
* Type 2 to search by person's name
* Type 3 to save a contact
 */

package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        displaymenu();
    }

    public static void findNumber(String number) {

        try(Scanner in = new Scanner(new File("file.txt"))) {
            String s[];

            boolean foundPerson = false;
            while(in.hasNextLine()) {
                s = in.nextLine().split(":");
                if (s[1].equals(number)) {
                    System.out.println("The number " + s[1] +  " is associated with the contact " + s[0]);
                    foundPerson = true;
                }
            }

            if(!foundPerson) {
                System.out.println("Could not find" + number);
            }

        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void findName(String name) {
        try(Scanner in = new Scanner(new File("file.txt"))) {
            String ss[];

            boolean foundNumber = false;
            while(in.hasNextLine()) {
                ss = in.nextLine().split(":");
                if (ss[0].equals(name)) {
                    System.out.println("The name " + ss[0] +  " is associated with the number " + ss[1]);
                    foundNumber = true;
                }
            }

            if(!foundNumber) {
                System.out.println("Could not find " + name);
            }

        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void saveContact(String name, long number){
        System.out.println("Saving contact " + name + ":" + number);

        try (PrintWriter pw = new PrintWriter(new FileWriter("file.txt", true))){
            pw.println(name +":"+ number);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displaymenu() {

        try {
            Scanner in = new Scanner(System.in);

            System.out.println("---------------------------------------");
            System.out.println("Welcome to Phone Book v0.1 alpha");
            System.out.println("---------------------------------------");
            System.out.println("Press 1 to search a phone number");
            System.out.println("---------------------------------------");
            System.out.println("Press 2 to search a person's name");
            System.out.println("---------------------------------------");
            System.out.println("Press 3 to save a person's contact info");
            System.out.println("---------------------------------------");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nWhat phone number would you like to find? (0040741159434)\n");
                    findNumber(in.nextLine());
                    break;

                case 2:
                    System.out.println("\nWhat is the person's name would you like to find? (FirstName Lastname)\n");
                    findName(in.nextLine());
                    break;

                case 3:
                    System.out.println("\nWhat is the person's info you would like to save (FirstName LastName)");
                    String name = in.nextLine();

                    System.out.println("\nWhat is the phone number of the person you want to save? (0040741159434)");
                    long number = in.nextLong();
                    in.nextLine();

                    saveContact(name, number);
                    break;

                default:

                    break;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }






}
