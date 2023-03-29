package Guessing_Games;

import java.util.Random;
import java.util.Scanner;

public class Code {
    Random random = new Random();
    int computerChoice = random.nextInt(10);
    Scanner in =new Scanner(System.in);
    public void input(){
        System.out.println("\n****Choose a level you want to play.****.");
        System.out.println("1. Easy\n2. Medium \n3. Hard");
        int n = in.nextInt();
        System.out.println(n);


        switch(n)
        {
            // easy mode user class ko object bana ra data member acess garne.
            case 1:
            {
                User easy = new User();
                int user = easy.userInput();
                if(user==computerChoice){
                    System.out.println("Congratulation you have selected correct number .");
                }
                else if(user>computerChoice){
                    System.out.println("Guess lower number.");
                }
                else{
                    System.out.println("Guess higher number.");
                }
            }

            // medium mode
            case 2:
            {
                User easy = new User();
                int user = easy.userInput();
                if(user<computerChoice){
                    System.out.println("Guess higher number.");
                }
//                else if(user>computerChoice){
//                    System.out.println("Guess lower number.");
//                }
                else{
                    System.out.println("Congratulation you have selected correct number .");
                }

            }
            // hard mode
            case 3:
            {

            }
        }
    }
}
