package lab2;
import java.util.Random;

public class GuessingGame {
    private int number;
    private int counter;

    public void GuessingGame(){
        number = generateRandomNumber();
        counter = 0;
    }

    public int makeGuess(int guess){
        counter++;
        return compareGuess(guess);
    }
    private int compareGuess(int guess) {
        if(guess < this.number){
            return 1;
        }
        if (guess > this.number){
            return -1;
        }

        return 0;

    }
    public int getCounter(){
        return counter;
    }

    private int generateRandomNumber(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        return randomNumber;
    }

}
