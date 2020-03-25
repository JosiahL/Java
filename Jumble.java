//Josiah Lovin
//Jumble.java
import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;
import java.util.Random;
//Desc:   This program displays a scrambled word and prompts for its original word.
//        If the user answers correctly, the program will output a correct message
//        and display another scrambled word. If the user answers incorrectly, the
//        program will prompt the user to try again. When prompted for an answer,
//        the user can always enter "a" to find out the correct answer, or "q" to quit
//        the program immediately. Otherwise, the program will end after all scrambled
//        words have been asked. In either case, the program displays a summary report
//        when it ends.
//Input:  When the user is prompted for the original word, the user enters a String,
//        which can be any word, "a", or "q".
//Output: For each scrambled word prompted, "Correct" is printed if the user's
//        answer is correct, "Try again" is printed if the user's guess is incorrect,
//        and the original word is printed if the user enters "a". Also, a summary report
//        giving the number of scrambled words asked, the number of correct
//        answers, and the correct percentage printed when the program ends.
public class Jumble
{
    public static String[] word = {"apple", "hotel", "dummy", "orange", "violet",
                                   "prompt", "summary", "correct", "display",
                                   "software", "parallel", "iterator"};
    public static int numCorrect = 0;
    public static int cur = 0;
    public static void main(String[] args)
    {
        boolean quit = false;
        while((cur<word.length) && (quit==false))
        {
            quit = play();
            cur++;
        }
        printReport();
    }
    //Desc:   Displays a scrambled word whose original counterpart is word[cur], and checks
    //        if the user enters the correct answer. The user will be asked to guess again
    //        and again until the user enters the correct answer, or until the user enters 'a' or 'q'
    //Post:   numCorrect is incremented by 1 if the user enters the correct answer
    //Output: Various messages to help the user play the game
    //Return: True is returned if the user wants to quit the game; false is retuned otherwise
    private static boolean play()
    {
        Scanner f = new Scanner(System.in);
        String answer;
        System.out.println(jumble(word[cur]));
        while(true)
        {
            answer = f.nextLine().toLowerCase();
            if(answer.equals(word[cur]))
            {
                System.out.println("Correct!");
                numCorrect++;
                return false;
            }
            else if(answer.equals("a"))
            {
                System.out.println(word[cur]);
                return false;
            }
            else if(answer.equals("q"))
            {
                System.out.println(word[cur]);
                return true;
            }
            else System.out.println("Incorrect. Try again.");
        }
    }
    //Output: A summary report giving the number of words asked, the number
    //        of correct answers, and the correct percentage printed.
    private static void printReport()
    {
        double percent = ((double)numCorrect/(double)cur)*100;
        System.out.printf("%d out of %d for %.2f%s\n", numCorrect, cur, percent, "%");
    }
    //Return: A string that is a permutation of the characters in s.
    public static String jumble(String s)
    {
        String jumbledWord = "";
        Random r = new Random();
        Vector<Character> v = new Vector<Character>();
        for(int i=0; i<s.length(); ++i)
        {
            if(r.nextInt(2)==0)
                v.add(0, s.charAt(i));
            else
                v.add(s.charAt(i));
        }
        Iterator<Character> iter = v.iterator();
        while(iter.hasNext())
            jumbledWord += iter.next();
        return jumbledWord;
    }
}