import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class TheGame extends JFrame implements ActionListener{
	
	XOGame game;
	Home homepage;
	Board board;
	Rules rules;
	HallOfFame hof;
	Player one;
	Player two;
	
	public TheGame(){
		
		rules = new Rules();
		homepage = new Home();
		hof = new HallOfFame();
		
		homepage.pack();
		homepage.setSize(350, 300);
		homepage.setLocationRelativeTo(null);
		homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepage.setVisible(true);
		
		rules.pack();
		rules.setSize(500,200);
		rules.setLocationRelativeTo(null);
		rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		hof.pack();
		hof.setSize(400,210);      // to be edited
		hof.setLocationRelativeTo(null);
		hof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		homepage.a.addActionListener(this);
		homepage.b.addActionListener(this);
		homepage.c.addActionListener(this);
		homepage.d.addActionListener(this);
		homepage.menuItem1.addActionListener(this);
		homepage.menuItem2.addActionListener(this);
		homepage.menuItem3.addActionListener(this);
		homepage.menuItem4.addActionListener(this);
		homepage.menuItem5.addActionListener(this);
		
		rules.b.addActionListener(this);
		
		hof.b.addActionListener(this);
		hof.c.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Exit"))
			System.exit(0);
		
		if (cmd.equals("Help") || cmd.equals("Rules")){
			rules.setVisible(true);
			homepage.setVisible(false);
		}
		
		if (cmd.equals("Back")){
			JButton temp = (JButton)e.getSource();
			if (temp.equals(rules.b)){
				rules.setVisible(false);
				homepage.setVisible(true);
			}
			else if (temp.equals(hof.b)){
				hof.setVisible(false);
				homepage.setVisible(true);
			}
		}
		
		if (cmd.equals("Clear")){
			int choice = JOptionPane.showConfirmDialog(this, "Do you want to clear the scores?");
			if (choice == 0){
			String fileName = "HallOfFame.txt";
			File file = new File(fileName);
			file.delete();
			hof.t.setText(this.printHOF());
			hof.c.setEnabled(false);
			}
		}
		
		if (cmd.equals("About")){
			JOptionPane.showMessageDialog(this, "<html>This is an X-O Game.<br>Created by Mohamed Abd El-Azeem.<br>Supervised by Hossam Amer.<br>Version 1.0.<br>2013-14.</html>", "About", JOptionPane.PLAIN_MESSAGE);
		}
		
		if (cmd.equals("End")){
			int choice = JOptionPane.showConfirmDialog(this, "Do you want to end this game?");
			
			if (choice == 0){
				for(int i = 0; i < board.b.length; i++){
					board.b[i].setEnabled(true);
					board.b[i].setText("");
				}
					board.setVisible(false);
					homepage.setVisible(true);
					this.HOF();
				}
				
		}
		
		if(cmd.equals("New Game")){         // not complete
			Object [] p = {"3", "4", "5"};
			String s = (String)JOptionPane.showInputDialog(this, "Please choose the size of the board:", "Board Size", JOptionPane.PLAIN_MESSAGE, null, p, "3");
			// instead of p --> null, instead of "3" --> null.
			
			if (s != null){
				String name1 = "";
				String name2 = "";
				do {
					name1 = (String)JOptionPane.showInputDialog(this, "Please enter Player 1's name", "Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
					if (name1 == null)
						break;
				}
				while (!this.checkName(name1));
				if (name1 != null) {
				one = new Player(name1);
				
				do {
					name2 = (String)JOptionPane.showInputDialog(this, "Please enter Player 2's name", "Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
					if (name2 == null)
						break;
					if (name2.equals(name1))
					    JOptionPane.showMessageDialog(this, "That's Player 1's name!", "Error", JOptionPane.PLAIN_MESSAGE);
				}
				while ((name2.equals(name1))  || (!this.checkName(name2)));
				if (name2 != null) {
				two = new Player(name2);
				
				int size = Integer.parseInt(s);
				game = new XOGame();
				game.newGame(size);
				board = new Board(size);
				for(int i = 0; i < size*size; i++){
					board.b[i].addActionListener(this);
				}
				board.r.addActionListener(this);
				board.e.addActionListener(this);
				board.u.addActionListener(this);
				
				homepage.setVisible(false);
				
				board.pack();
				board.setSize(600,300);
				board.setLocationRelativeTo(null);
				board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				board.setVisible(true);
				
				game.board();
				board.l.setText("" + one.Name + "'s turn");
				}
				}
			}
				
		}
		
		if (cmd.equals("")){
			
		
		int x, y = 0;
		JButton B = (JButton)(e.getSource());
		B.setEnabled(false);
		int i = 0;
		outerloop:
		for (x = 1; x <= 3; x++){
			for (y = 1; y <= 3; y++){
				if (B.equals(board.b[i]))
					break outerloop;
				i++;
			}
		}
		
		game.insert(x, y-1);
		board.u.setEnabled(true);
		
		if (game.i % 2 == 0)
			board.l.setText("" + one.Name + "'s turn");
		else if (game.i % 2 != 0)
			board.l.setText("" + two.Name + "'s turn");
		
		B.setText("" + game.symbol);
		game.board();
		if (game.check()){
			board.l.setText("");
			board.u.setEnabled(false);
			if (game.winner == 'X'){
				JOptionPane.showMessageDialog(this, "" + one.Name + " has won!", "Winner", JOptionPane.PLAIN_MESSAGE);
				one.score++;
			}
			else if (game.winner == 'O'){
				JOptionPane.showMessageDialog(this, "" + two.Name + " has won!", "Winner", JOptionPane.PLAIN_MESSAGE);
				two.score++;
			}
			for (int j = 0; j < board.b.length; j++){
				board.b[j].setEnabled(false);
			}
		}
		else {
			if (game.i == board.b.length){
				JOptionPane.showMessageDialog(this, "Draw!", "Tie", JOptionPane.PLAIN_MESSAGE);
			for (int j = 0; j < board.b.length; j++){
				board.b[j].setEnabled(false);
			}
			board.l.setText("");
			board.u.setEnabled(false);		
			}
		}
		}
		
		if (cmd.equals("Undo")){
			game.undo();
			game.board();
			if (game.i % 2 == 0)
				board.l.setText("" + one.Name + "'s turn");
			else if (game.i % 2 != 0)
				board.l.setText("" + two.Name + "'s turn");
			int x = Integer.parseInt("" + game.last.charAt(0));
			int y = Integer.parseInt("" + game.last.charAt(1));
			int i = (((int)Math.sqrt(board.b.length))*(x-1))+y;
			board.b[i].setText("");
			board.b[i].setEnabled(true);
			board.u.setEnabled(false);
		}
		
		if (cmd.equals("Reset")){
			game = new XOGame();
			game.newGame((int)Math.sqrt(board.b.length));
			game.board();
			board.l.setText("" + one.Name + "'s turn");
			for(int i = 0; i < board.b.length; i++){
				board.b[i].setEnabled(true);
				board.b[i].setText("");
				}
		}
		
		if (cmd.equals("Hall of Fame") || (cmd.equals("Scores"))){
			String h = this.printHOF();
			if (h.equals("No scores yet!"))
				hof.c.setEnabled(false);
			else hof.c.setEnabled(true);
			hof.t.setText(h);
			hof.setVisible(true);
			homepage.setVisible(false);
		}
		
	}
	
	public boolean checkName(String s){
		
		if (s.equals("")){
			JOptionPane.showMessageDialog(this, "Field is empty!", "Error", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		else {
			for (int i = 0; i < s.length(); i++){
				if (s.charAt(i) != ' ')
					break;
				
				if ((i == s.length()-1) && (s.charAt(i) == ' ')){
					JOptionPane.showMessageDialog(this, "Field is empty!", "Error", JOptionPane.PLAIN_MESSAGE);
					return false;
		        }
			}
			
			String fileName = "HallOfFame.txt";
			File file = new File(fileName);
			try {
				Scanner in = new Scanner(file);
				in.nextLine();
				String s2 = in.nextLine();
				
				String [] S2 = s2.split(",");
				
				for (int j = 0; j < S2.length; j++){
					if (s.equalsIgnoreCase(S2[j])){
						in.close();
						JOptionPane.showMessageDialog(this, "Name already taken!", "Error", JOptionPane.PLAIN_MESSAGE);
						return false;
					}
				}
				
				in.close();
				return true;
			    
			} catch (FileNotFoundException e) {
				return true;
			}
			
		}
		
	}
	
	public void HOF(){
		
		String fileName = "HallOfFame.txt";
		
		try {
			FileWriter output = new FileWriter(fileName, true);
			  
				String FName = "HallOfFame.txt";
				File File = new File(FName);
				
				try {
					Scanner In = new Scanner(File);
					
					String x1 = "";
					String x2 = "";
					
					if (In.hasNextLine()){
					x1 = In.nextLine();
					x2 = In.nextLine();
					output = new FileWriter(fileName);
					}
					
					//if ((one.score != 0) || (two.score != 0)){
					output.write(x1 + one.score + "," + two.score + ",");
					output.write("\r\n");
					output.write(x2 + one.Name + "," + two.Name + ",");
					//}
					//else {
						//output.write(x1);
						//output.write("\r\n");
						//output.write(x2);
					//}
					
					output.close();
					In.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();	
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String printHOF(){
		
		String fileName = "HallOfFame.txt";
		File file = new File(fileName);
		String scores = "";
		try {
			Scanner in = new Scanner(file);
			String s1 = in.nextLine();
			String s2 = in.nextLine();
			
			String [] S1 = s1.split(",");
			String [] S2 = s2.split(",");
			
			for (int i = 0; i < S1.length; i++){
				int max = i;
				for (int j = i; j < S1.length; j++){
					
					if (Integer.parseInt(S1[j]) > Integer.parseInt(S1[max]))
						max = j;
				}
				
				String temp = S1[i];
				S1[i] = S1[max];
				S1[max] = temp;
				
				temp = S2[i];
				S2[i] = S2[max];
				S2[max] = temp;
				
			}
			in.close();
			for (int k = 0; k < S1.length; k++){
				scores += S2[k] + ": " + S1[k] + "\n";
			}
			
		} catch (FileNotFoundException e) {
			scores = "No scores yet!";
		}
		
		return scores;
		
	}
	
	public static void main(String[] args) {
		
		TheGame g = new TheGame();
		
	}

}