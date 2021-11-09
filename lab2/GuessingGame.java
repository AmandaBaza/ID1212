import java.util.Random;

public class GuessingGame {
    private int number;
    private int counter;

    public void GuessingGame(){
        number = generateRandomNumber();
        counter = 0;
    }

    public boolean makeGuess(int guess){
        counter++;
        return isNumber(guess);
    }
    public int getCounter(){
        return counter;
    }

    private boolean isNumber(int guess){
        return (guess==number);
    }

    private int generateRandomNumber(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        return randomNumber;
    }

}
