import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest
{
    Hangman game;

    @BeforeEach
    void setUp()
    {
        game = new Hangman("test");
    }

    @Test
    void testInput()
    {
        game.testInput("t");
        assertTrue(game.getRightGuesses().contains("t"));
        assertFalse(game.getWrongGuesses().contains("t"));

        game.testInput("w");
        assertTrue(game.getWrongGuesses().contains("w"));
        assertFalse(game.getRightGuesses().contains("w"));

        assertEquals(game.getAnswer(), "test");
        assertEquals(game.getAnswerLength(), 4);
    }

    @Test
    void testState()
    {
        for (char c = 'a'; c < 'h'; c++)
        {
            game.testInput(String.valueOf(c));
        }

        // 0-6 gives 7 attempts.
        assertEquals(game.getState(), 6);
    }

    @Test
    void testNewGame()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("test");
        list.add("blue");
        list.add("key");
        list.add("canon");
        list.add("theory");
        list.add("valve");
        list.add("botany");
        list.add("electricity");
        list.add("element");
        list.add("gravity");
        list.add("mass");
        list.add("bed");
        list.add("coffee");
        list.add("camera");
        list.add("girl");
        list.add("boy");
        list.add("history");
        list.add("information");
        list.add("include");
        list.add("snow");

        game.newGame();
        assertTrue(list.contains(game.getAnswer()));
    }

   @AfterEach
   void tearDown()
   {
   }
}