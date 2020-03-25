//Josiah Lovin
//TicTacToe.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Desc:   class TicTacToe uses JFrame to dispaly a GUI of a game of Tic-Tac-Toe.
//        The game is played by simply clicking on the individual buttons.
//        The first click is "X's" turn, the second click "O's" turn, and so on.
//Input:  Users simply click the buttons to play their turn.
//Output: The buttons display where each user has played.
//        A label above the game welcomes the user and displays the end result of
//        the game (who won or tie).
public class TicTacToe extends JFrame implements ActionListener
{
    char turn = 'X';
    private char[][] board = new char[3][3];
    private JLabel lblTitle = new JLabel("Welcome to Josiah's TicTacToe");
    private JPanel pnlTop = new JPanel();

    private JButton btn1 = new JButton();
    private JButton btn2 = new JButton();
    private JButton btn3 = new JButton();
    private JButton btn4 = new JButton();
    private JButton btn5 = new JButton();
    private JButton btn6 = new JButton();
    private JButton btn7 = new JButton();
    private JButton btn8 = new JButton();
    private JButton btn9 = new JButton();
    private JPanel pnlMain = new JPanel();
    //Desc: Constructor for class TicTacToe. Sets up the TicTacToe JFrame.
    //Post: Adds visual details and registers action listeners
    public TicTacToe()
    {
        setSize(800, 800);
        setLocationRelativeTo(null);
        Font myFont = new Font("Serif", Font.BOLD, 40);
        btn1.setFont(myFont);
        btn2.setFont(myFont);
        btn3.setFont(myFont);
        btn4.setFont(myFont);
        btn5.setFont(myFont);
        btn6.setFont(myFont);
        btn7.setFont(myFont);
        btn8.setFont(myFont);
        btn9.setFont(myFont);
        add(pnlTop, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        addControlsPnlTop();
        addControlsPnlMain();
        registerListeners();
        setTitle("TicTacToe.java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //Post: Add the title to the top of the JFrame
    public void addControlsPnlTop()
    {
        pnlTop.add(lblTitle);
    }
    //Post: Add the 3 x 3 button grid to the middle panel
    public void addControlsPnlMain()
    {
        pnlMain.setLayout(new GridLayout(3, 3));
        pnlMain.add(btn1);
        pnlMain.add(btn2);
        pnlMain.add(btn3);
        pnlMain.add(btn4);
        pnlMain.add(btn5);
        pnlMain.add(btn6);
        pnlMain.add(btn7);
        pnlMain.add(btn8);
        pnlMain.add(btn9);
    }
    //Post: Register the button to be action listeners
    public void registerListeners()
    {
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
    }
    //Input:  User presses buttons on game board
    //Output: X or O on buttons, label displays messages
    //Post:   When a button is pressed, tnter the turn into array "board", and remove the action listener from the button.
    //        Checks to see if the game has ended in a winner or tie. If the game is over, all buttons are disabled.
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn1)
        {
            btn1.setText(Character.toString(turn));
            board[0][0] = turn;
            btn1.removeActionListener(this);
        }
        else if(e.getSource() == btn2)
        {
            btn2.setText(Character.toString(turn));
            board[0][1] = turn;
            btn2.removeActionListener(this);
        }
        else if(e.getSource() == btn3)
        {
            btn3.setText(Character.toString(turn));
            board[0][2] = turn;
            btn3.removeActionListener(this);
        }
        else if(e.getSource() == btn4)
        {
            btn4.setText(Character.toString(turn));
            board[1][0] = turn;
            btn4.removeActionListener(this);
        }
        else if(e.getSource() == btn5)
        {
            btn5.setText(Character.toString(turn));
            board[1][1] = turn;
            btn5.removeActionListener(this);
        }
        else if(e.getSource() == btn6)
        {
            btn6.setText(Character.toString(turn));
            board[1][2] = turn;
            btn6.removeActionListener(this);
        }
        else if(e.getSource() == btn7)
        {
            btn7.setText(Character.toString(turn));
            board[2][0] = turn;
            btn7.removeActionListener(this);
        }
        else if(e.getSource() == btn8)
        {
            btn8.setText(Character.toString(turn));
            board[2][1] = turn;
            btn8.removeActionListener(this);
        }
        else
        {
            btn9.setText(Character.toString(turn));
            board[2][2] = turn;
            btn9.removeActionListener(this);
        }
        //Check to see if there is a winner. If there is, change the title to display
        //the winner, change the winning letters to green, and disable all buttons.
        if(board[0][0]==board[0][1] && board[0][0]==board[0][2] && board[0][0]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn1.setForeground(Color.GREEN);
            btn2.setForeground(Color.GREEN);
            btn3.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[1][0]==board[1][1] && board[1][0]==board[1][2] && board[1][0]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn4.setForeground(Color.GREEN);
            btn5.setForeground(Color.GREEN);
            btn6.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[2][0]==board[2][1] && board[2][0]==board[2][2] && board[2][0]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn7.setForeground(Color.GREEN);
            btn8.setForeground(Color.GREEN);
            btn9.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[0][0]==board[1][0] && board[0][0]==board[2][0] && board[0][0]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn1.setForeground(Color.GREEN);
            btn4.setForeground(Color.GREEN);
            btn7.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[0][1]==board[1][1] && board[0][1]==board[2][1] && board[0][1]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn2.setForeground(Color.GREEN);
            btn5.setForeground(Color.GREEN);
            btn8.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[0][2]==board[1][2] && board[0][2]==board[2][2] && board[0][2]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn3.setForeground(Color.GREEN);
            btn6.setForeground(Color.GREEN);
            btn9.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn1.setForeground(Color.GREEN);
            btn5.setForeground(Color.GREEN);
            btn9.setForeground(Color.GREEN);
            gameOver();
        }
        else if(board[0][2]==board[1][1] && board[0][2]==board[2][0] && board[0][2]!='\u0000')
        {
            lblTitle.setText("Game over, " + turn + " wins!");
            btn7.setForeground(Color.GREEN);
            btn5.setForeground(Color.GREEN);
            btn3.setForeground(Color.GREEN);
            gameOver();
        }
        //If no winner, check to see if the board is filled. If so, the label displays
        //that the game ended in a tie and all buttons are disabled.
        else if(board[0][0]!='\u0000' && board[0][1]!='\u0000' && board[0][2]!='\u0000' &&
                board[1][0]!='\u0000' && board[1][1]!='\u0000' && board[1][2]!='\u0000' &&
                board[2][0]!='\u0000' && board[2][1]!='\u0000' && board[2][2]!='\u0000')
        {
            gameOver();
            lblTitle.setText("It's a tie!");
        }
        //Change turns
        if(turn=='X')
            turn = 'O';
        else
            turn = 'X';
    }
    //Post: Removes action listeners from all buttons.
    public void gameOver()
    {
        btn1.removeActionListener(this);
        btn2.removeActionListener(this);
        btn3.removeActionListener(this);
        btn4.removeActionListener(this);
        btn5.removeActionListener(this);
        btn6.removeActionListener(this);
        btn7.removeActionListener(this);
        btn8.removeActionListener(this);
        btn9.removeActionListener(this);
    }
    //Post: Open the TicTacToe JFrame
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
    }
}