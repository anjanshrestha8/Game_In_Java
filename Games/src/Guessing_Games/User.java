package Guessing_Games;

import java.util.Scanner;

public class User {
    Scanner in =new Scanner(System.in);
    public int  userInput(){
        int userChoice;
        System.out.println("Enter your guess from 0 to 10 number.");
        userChoice = in.nextInt();
        return userChoice;
    }

}
