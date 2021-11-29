import java.util.Random;

public class GuessingGame {
    private int secretNumber;
    private int totalNumberOfGuesses;
    private boolean guessedCorrect;
    String responseHtml;

    public void GuessingGame(){
        secretNumber = generateRandomNumber();
        totalNumberOfGuesses = 0;
        guessedCorrect = false;
    }

    public String makeGuess(int guess){
        totalNumberOfGuesses++;
        return compareGuess(guess);
    }
    private String compareGuess(int guess) {
        if(guess < this.secretNumber){
            return "You guessed "+guess+" the secret number is higher";
        }
        if (guess > this.secretNumber){
            return "You guessed "+guess+" the secret number is lower";
        }
        guessedCorrect = true;
        return "Congratulation you guessed "+guess+" witch is the secret number";


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
