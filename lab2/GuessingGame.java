import java.util.Random;

public class GuessingGame {
    private int secretNumber;
    private int totalNumberOfGuesses;
    private boolean guessedCorrect;

    public void GuessingGame(){
        secretNumber = generateRandomNumber();
        totalNumberOfGuesses = 0;
        guessedCorrect = false;
    }

    public int makeGuess(int guess){
        totalNumberOfGuesses++;
        return compareGuess(guess);
    }
    private int compareGuess(int guess) {
        if(guess < this.secretNumber){
            return 1;
        }
        if (guess > this.secretNumber){
            return 2;
        }
        guessedCorrect = true;
        return 0;


    }
    public int getGuesses(){
        return totalNumberOfGuesses;
    }

    public boolean getGuessedCorrect(){
        return guessedCorrect;
    }

    private int generateRandomNumber(){
        Random rand = new Random();
        int randomNumber = (rand.nextInt(100))+1;
        return randomNumber;
    }

}
