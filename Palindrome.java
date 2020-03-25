//Josiah Lovin
//Palindrome.java
import java.util.Scanner;
public class Palindrome
{
    //Desc:    Driver for the recursive "isPalindrome" method
    //Input:   User enters a word in all lowercase letters, or enters "q" to quit
    //Output:  Program outputs "true" if the word is a palindrome, or
    //        "false" if it is not a palindrome
    public static void main(String[] args)
    {
        String entry = "s";
        while(entry.charAt(0)!='q' || entry.length()!=1)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter a String: ");
            entry = keyboard.nextLine();
            System.out.println(isPalindrome(entry, 0, entry.length()-1));
        }
    }
    //Desc:   Determines if a string is a palindrome. A palindrome is a string that
    //        reads the same forward and backward
    //Pre:    s consists of lowercase letters only
    //Return: true if string s in index [first, last) is a palindrome
    public static boolean isPalindrome(String s, int first, int last)
    {
        if(first==last || first>last)
            return true;
        else if(s.charAt(first)==s.charAt(last))
            return isPalindrome(s, first+1, last-1);
        else
            return false;
    }
}