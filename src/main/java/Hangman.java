import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman
{

    private ArrayList<String> wordlist;
    private ArrayList<String> wrongguesses;
    private ArrayList<String> rightguesses;
    private String answer;
    private int attempts;
    private Scanner in;

    /**
     * Default hangman constructor. Will randomly select the secret word.
     */
    public Hangman()
    {
        this.attempts = 0;
        this.wordlist = addWords();
        this.answer = wordlist.get(new Random().nextInt(wordlist.size() - 1));
        this.wrongguesses = new ArrayList<>();
        this.rightguesses = new ArrayList<>();
        in = new Scanner(System.in);
    }

    /**
     * Hangman constructor that will utilize the supplied word as the secret word.
     * @param word the secret word.
     */
    public Hangman(String word)
    {
        this.answer = word;
        this.attempts = 0;
        this.wrongguesses = new ArrayList<>();
        this.rightguesses = new ArrayList<>();
        in = new Scanner(System.in);
    }

    /**
     *
     * @return the list of wrong guesses.
     */
    public ArrayList<String> getWrongGuesses()
    {
        return wrongguesses;
    }

    /**
     *
     * @return the list of right guesses.
     */
    public ArrayList<String> getRightGuesses()
    {
        return rightguesses;
    }

    /**
     *
     * @return the length of the current secret word.
     */
    public int getAnswerLength()
    {
        return answer.length();
    }

    /**
     *
     * @return the secret word.
     */
    public String getAnswer()
    {
        return answer;
    }

    /**
     *
     * @return the number of attempts.
     */
    public int getState()
    {
        return attempts;
    }

    /**
     *  Starts the hangman game.
     */
    public void start()
    {
        System.out.println("HANGMAN \n");
        System.out.println(stateToString(getState()));

        while (getState() < 7)
        {
            checkGuess(guess());
            System.out.println();
            System.out.println(stateToString(getState()));

            if (checkWin())
            {
                System.out.println("Yes! The secret word is \"" + getAnswer() + "\"! You have won!");
                break;
            }
        }
        if (getState() == 7)
        {
            System.out.println("Aw... You lost.. Better luck next time!");
        }
    }

    /**
     *
     * @return the current guess.
     */
    private String guess()
    {
        String temp;
        while(true)
        {
            System.out.println("Guess a letter. \n\n");
            try
            {
                temp = in.nextLine().strip().toLowerCase();
                if (temp.length() > 1 || getRightGuesses().contains(temp)
                        || getWrongGuesses().contains(temp)
                        || !temp.matches("[a-z]+"))
                {
                    System.out.println("Oops invalid entry, try again!");
                    continue;
                }
                return temp;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Checks to see if the supplied character is part of the secret word.
     * @param guess the guess to be checked.
     */
    private void checkGuess(String guess)
    {
        if (!getAnswer().contains(guess))
        {
            attempts++;
            wrongguesses.add(guess);
        }
        else
        {
            rightguesses.add(guess);
        }
    }

    /**
     *
     * @param state the current number of attempted guesses
     * @return the string value of the current state.
     */
    private String stateToString(int state)
    {
        StringBuilder result = new StringBuilder();
        switch (state)
        {
            case 1:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                              "     |\n" +
                              "     |\n" +
                              "    ===\n\n");
                break;
            }
            case 2:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                              " |   |\n" +
                              "     |\n" +
                              "    ===\n\n");
                break;
            }
            case 3:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                              " |   |\n" +
                              " |   |\n" +
                              "    ===\n\n");
                break;
            }
            case 4:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                             "\\|   |\n" +
                              " |   |\n" +
                              "    ===\n\n");
                break;
            }
            case 5:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                             "\\|/  |\n" +
                              " |   |\n" +
                              "    ===\n\n");
                break;
            }
            case 6:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                             "\\|/  |\n" +
                              " |   |\n" +
                              "/   ===\n\n");
                break;
            }
            case 7:
            {
                result.append(" +---+\n" +
                              " O   |\n" +
                             "\\|/  |\n" +
                              " |   |\n" +
                              "/ \\ ===\n\n");
                break;
            }
            default:
                result.append("+---+\n" +
                              "    |\n" +
                              "    |\n" +
                              "    |\n" +
                              "   ===\n\n");
        }

        result.append("Missed letters: ");
        result.append(getWrongGuesses());
        result.append("\n\n");

        for (int i = 0; i < getAnswerLength(); i++)
        {
            boolean found = false;
            for (String s : getRightGuesses())
            {

                if (getAnswer().substring(i, i + 1).equals(s))
                {
                    found = true;
                    result.append(s);
                }
            }
            if (!found)
            {
                result.append("_ ");
            }
        }
        result.append("\n\n");
        System.out.println(getRightGuesses());
        return result.toString();
    }

    private boolean checkWin()
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < getAnswerLength(); i++)
        {
            boolean found = false;
            for (String s : getRightGuesses())
            {

                if (getAnswer().substring(i, i + 1).equals(s))
                {
                    found = true;
                    result.append(s);
                }
            }
            if (!found)
            {
                result.append("_");
            }
        }
        return result.toString().equals(getAnswer());
    }

    /**
     * method to add words so adding words from another location would
     * be easier to implement in the future.
     * @return an arraylist with words that can be used as the secret word.
     */
    private ArrayList<String> addWords()
    {
        ArrayList<String> result = new ArrayList<>();
        result.add("test");
        result.add("blue");
        result.add("key");
        result.add("canon");
        result.add("theory");
        result.add("valve");
        result.add("botany");
        result.add("electricity");
        result.add("element");
        result.add("gravity");
        result.add("mass");
        result.add("bed");
        result.add("coffee");
        result.add("camera");
        result.add("girl");
        result.add("boy");
        result.add("history");
        result.add("information");
        result.add("include");
        result.add("snow");
        return result;
    }

    /**
     * setup to start a new game.
     */
    public void newGame()
    {
        this.attempts = 0;
        this.wordlist = addWords();
        this.answer = wordlist.get(new Random().nextInt(wordlist.size() - 1));
        this.wrongguesses.clear();
        this.rightguesses.clear();
    }

    /**
     * setup to start a new game with the specified secret word.
     * @param word the new secret word.
     */
    public void newGame(String word)
    {
        this.answer = word;
        this.attempts = 0;
        this.wrongguesses.clear();
        this.rightguesses.clear();
    }

    /**
     * method for testing input.
     * @param str the letter to be tested against the secret word.
     */
    public void testInput(String str)
    {
        checkGuess(str);
    }



}
