package com.example.examplefeature.ui;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class NumberGuessing {
    // Important Properties of Numberguessing game 
    final int Max_Attempts = 15;
    int counter = Max_Attempts;
    int Secret_Number;
    final ArrayList<Integer> storage = new ArrayList<>();

    int User_guess;


         public NumberGuessing(){
            Hardcoded_Logic();
       }

        public void Hardcoded_Logic() {
        storage.clear();
        counter = Max_Attempts;
        Secret_Number = ThreadLocalRandom.current().nextInt(1,31);
        }

      


        // May optimize this for front end service
        public String GameFeedbackHints() {
            int distance = Math.abs(User_guess - Secret_Number);



            if (distance == 1) {
                return("You're close 🔥");
            } 

            else if (distance <=2 ){
                return("You're warm ☕️");

            }

            else {
                return("You're cold 🥶");
            }
        }

        public String Checks(int pendingGuess){
            this.User_guess = pendingGuess;

            if (storage.contains(User_guess)){
               return("You already guessed that! Try again.");
                
            } 
       

            if (User_guess == Secret_Number){
                Hardcoded_Logic();
                return("You guessed the number!!! 💯 ");
                
            } else {
                     storage.add(User_guess);
                     counter--;
            }

            if (counter == 0){

                int last_SecretNumber = Secret_Number;
                Hardcoded_Logic();
                return("You have run out of attempts \n The secret number was " + last_SecretNumber);
                
                
            }

            
            return GameFeedbackHints() + "| Attempts remaining " + counter;



            // Code for checking to see if stored, ✅ Answer, ❌ Wrong Answer
        }

       //  System.out.println(storage);  Test case to see if the Storage works (It works)
    
}



// Backend code 
