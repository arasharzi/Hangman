import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Hangman game = new Hangman();
        game.start();


        Scanner in = new Scanner(System.in);
        String temp;
        while (true)
        {
            System.out.println("Would you like to play again? (y or n) \n");
            try
            {
                temp = in.nextLine().toLowerCase().trim();
            }
            catch (Exception e)
            {
                System.out.println("\nInvalid input try again. \n");
                continue;
            }

            if (temp.equals("y"))
            {
                game.newGame();
                game.start();
            }
            else if (temp.equals("n"))
            {
                System.out.println("Thanks for playing, bye!");
                break;
            }
        }
        in.close();
    }
}
