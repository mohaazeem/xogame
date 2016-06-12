import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class XOGame{

	String [][] Board;
	int i;       // This counter helps in determining if the grid is full or not, as it indicates the number of cells in use. So the players can insert and undo as long as the grid is not full.
	int gridSize;
	Scanner input = new Scanner(System.in);   // I used the scanner instance as an instance variable because I wanted it to be seen globally.
	String last;          // This variable stores the coordinates of the last input. It serves a purpose in the undo operation.
	char winner;           // Saves the character that wins. Either 'X' or 'O'.
	char symbol;
	static Player one;
	static Player two;
	static int round = 0;
	
	public XOGame(){
		//this.Test = new test();
		//Test.pack();
		//Test.setLocationRelativeTo(null);
		//Test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Test.setVisible(true);
		i = 0;
	}
	
	public boolean checkName(String s){
		
		if (s.equals("")){
			System.out.println("Field is empty!");
			return false;
		}
		else {
			for (int i = 0; i < s.length(); i++){
				if (s.charAt(i) != ' ')
					break;
				
				if ((i == s.length()-1) && (s.charAt(i) == ' ')){
					System.out.println("Field is empty!");
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
						System.out.println("Name already Taken!");
						in.close();
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
	
	public void newGame(int size){
		
		/*if (round == 0) {
		
		boolean p = false;
		String s1 = ""; 
		
		while (p == false) {
		
		System.out.println("Enter Player 1's name:");
		s1 = input.nextLine();
		p = this.checkName(s1);
		
		}
		
		one = new Player(s1);
		
		p = false;
		
		while (p == false) {
			
		System.out.println("Enter Player 2's name:");
		s1 = input.nextLine();
		p = this.checkName(s1);
		
		}
		
		two = new Player(s1);
		
		round++;
		
		}*/
		
		/*String s1;
		System.out.println("Enter grid size in the format: nXn (where n is a number) or H for help or F for Hall of Fame or E to exit:");
		for (int c = 1; true; c++){
			s1 = input.nextLine();
			if ((s1.equals("h")) || (s1.equals("H"))) {
			System.out.println(this.help());
			this.newGame();
		}
			if ((s1.equals("e")) || (s1.equals("E"))){
				System.out.println("Bye!");
				this.HOF();
				System.exit(0);
			}
			if ((s1.equals("f")) || (s1.equals("F"))){
				this.printHOF();
				this.newGame();
			}
			if ((s1.length() != 3) || (s1.charAt(1) != 'X') || (((int)(s1.charAt(0)) < 48) || (int)(s1.charAt(0))> 57) || (((int)(s1.charAt(2)) < 48) || ((int)(s1.charAt(2)) > 57)) || (s1.charAt(0) != s1.charAt(2))){
			   if (c >= 3)
				   System.out.println("Wrong format! The right format is numberXnumber i.e. 7X7");
				   else
					   System.out.println("Wrong format! Please stick to nXn");
		  }
			else if (s1.charAt(0) == '2' || s1.charAt(2) == '2' || s1.charAt(0) == '1' || s1.charAt(2) == '1' || s1.charAt(0) == '0' || s1.charAt(2) == '0')
				System.out.println("The least value for n is 3!");
			else
				break;
		  }
		
		
		int size = Integer.parseInt("" + s1.charAt(0));
		this.Board = new String [size+1][size];
		this.gridSize = size;*/		
		
		this.Board = new String [size+1][size];
		this.gridSize = size;
		
		//System.out.println("Game Started!");
		//this.board();
		//this.printBoard();
		//this.start();
		
	
}
	//public void start(){    // in this method, the user is asked to enter the coordinates of their next input.
		
		
		//while (i < (gridSize*gridSize)){	
			
			/*System.out.println("Enter co-ordinates in the format: x,y or U for undo of E to exit");
			
			if (i%2 == 0)
				System.out.println(one.Name + "'s turn!");
			else if (i%2 != 0)
				System.out.println(two.Name + "'s turn!");
			
			String s2 = input.nextLine();
			if ((s2.equals("U")) || (s2.equals("u"))){
				this.undo();
				this.board();
				System.out.println("Undo!");
				this.start();
			}
			
			if ((s2.equals("e")) || (s2.equals("E"))){
				System.out.println("Bye!");
				this.HOF();
				System.exit(0);
			}
			
			else if ((s2.length() != 3) || (s2.charAt(1) != ',') || (((int)(s2.charAt(0)) < 48) || (int)(s2.charAt(0)) > 57) || (((int)(s2.charAt(2)) < 48) || ((int)(s2.charAt(2)) > 57))){
			System.out.println("Wrong Format! Please stick to (x,y)");
			this.start();
			}
			
			int x = Integer.parseInt("" + s2.charAt(0));
			int y = Integer.parseInt("" + s2.charAt(2));
			
			if ((x > gridSize) || (y > gridSize) || (x < 1) || (y < 1)){
				System.out.println("You can only enter digits between 1 and " + (gridSize) + " for both x and y!");
				this.start();
			}
			
			this.insert(x,y-1);*/
			
			//if (Test.flag1 == true){
			//Test.flag1 = false;
			//Test.flag2 = true;
			//String s2 = Test.getC();
			
			//int x = Integer.parseInt("" + s2.charAt(0));
			//int y = Integer.parseInt("" + s2.charAt(2));
			
			//this.insert(x,y-1);
			/**this.board();*/
			//this.printBoard();
			//if (this.check())
				//break;
			//}
			//if (Test.flag2 == true){
			//Test.flag2 = false;
			//this.start();          // The coordinates() method is called to allow the players to insert again.
			//}
		//}
		
		//System.out.println("Game Ended!");     // This line will be reached when i is equal to gridSize^2, which indicates that the grid is full and the game ended as a draw.
		//System.out.println("Do you want to start a new game? (Y/N)");
		

		/*while (true){
			
			String ss = input.nextLine();
						
		if (ss.equals("Y")){
			(new XOGame()).newGame();         // After the game has ended, a new game is initialized.
		}
		    else if (ss.equals("N")) {
			System.out.println("Bye!");
			round = 0;
			this.HOF();
			System.exit(0);
			
		   }
		else System.out.println("Wrong Format! Please enter Y or N!");
		
		}*/
	//}
	
	public void insert(int x, int y){
		last = "" + x + y;	
		//if ((Board[x][y].charAt(2) != 'X') && (Board [x][y].charAt(2) != 'O')){        // The cell is empty.
			
		if (i%2 == 0){                        // Player 1 will always use the 'X', whereas Player 2 will use the 'O'. So as i starts from 0, every time i is even an 'X' will be inserted, and for the odd values of i an 'O' will be inserted.
		i++;
		symbol = 'X';
		//Test.B.setText("" + Test.symbol);
		}
		else if (i%2 != 0){
		i++;
		symbol = 'O';
		//Test.B.setText("" + Test.symbol);
		}
		//}
		
		//else {
			//System.out.println("Position occupied!");
			//Test.symbol = Board[x][y].charAt(2);
		//}
	}
	
	public boolean check(){
		
		if (this.row() || this.column() || this.diagonalRight() || this.diagonalLeft()){         // After every insertion, the algorithm checks if a player has won.
			//if (winner == 'X'){
				//this.Test.t.setText("X wins.");
			//System.out.println(one.Name + " has won!");
			//one.score++;
			//}
			//else if (winner == 'O'){
				//this.Test.t.setText("O wins.");
			//System.out.println(two.Name + " has won!");
			//two.score++;
			//}
			
			return true;
			
	}
		
		else return false;
		
	}
	
	public void undo(){
		//if (i == 0)
			//System.out.println("Nothing to undo!");
		//else {
		symbol = ' ';
		i--;    // I decrement the i as I previously mentioned that it indicates the number of cells in use. So an undo will free a new cell.
		//}
	}
	
	public boolean row(){                  // Checks if all the cells in a row are identical, i.e. a player has won.
		
		for (int j = 1; j < Board.length; j++){
			for (int k = 0; k < Board.length-2; k++){
				if (Board[j][k].charAt(2) != Board[j][k+1].charAt(2))
					break;
				else if (Board[j][k].charAt(2) == '_')
					break;
				if (k == Board.length-3){
					this.winner = Board[j][k].charAt(2);
					return true;
				}
			}
		
		}
		return false;
	}
	
	public boolean column(){             // Checks if all the cells in a column are identical, i.e. a player has won.
		
		for (int j = 0; j < Board.length-1; j++){
			for (int k = 1; k < Board.length-1; k++){
				if (Board[k][j].charAt(2) != Board[k+1][j].charAt(2))
					break;
				else if (Board[k][j].charAt(2) == '_')
					break;
				if (k == Board.length-2){
					this.winner = Board[k][j].charAt(2);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean diagonalRight(){               // Checks if the cells forming the diagonal of this shape '\' are identical, i.e. a player has won.
		
		for (int j = 1; j < Board.length-1; j++){
				int k = j-1;				
				if (Board[j][k].charAt(2) != Board[j+1][k+1].charAt(2))
					break;
				else if (Board[j][k].charAt(2) == '_')
					break;
				if ((j == Board.length-2) && (k == Board.length-3)){
					this.winner = Board[j][k].charAt(2);
					return true;
				}
			
		}
		return false;
	}
	
	public boolean diagonalLeft(){                // Checks if the cells forming the diagonal of this shape '/' are identical, i.e. a player has won.
		
		for (int j = 1; j < Board.length-1; j++){
			int k = Board.length-1-j;
				if (Board[j][k].charAt(2) != Board[j+1][k-1].charAt(2))
					break;
				else if (Board[j][k].charAt(2) == '_')
					break;
				if ((j == Board.length-2) && (k == 1)){
					this.winner = Board[j][k].charAt(2);
					return true;
				}
			
		}
		return false;
	}
	
	public void board(){
		
		if (i == 0){
		for (int j = 0; j < Board.length; j++){
			for (int k = 0; k < Board[j].length; k++){
				if (j == 0)
					Board[j][k] = " ___";
				else
				{
					if (k != Board[j].length-1)
						Board[j][k] = "|___";
					else
						Board[j][k] = "|___|";
			    }
			 }
		  }
	   }
		else {
		   int x = Integer.parseInt("" + last.charAt(0));
		   int y = Integer.parseInt("" + last.charAt(1));
		   
		   if (symbol == ' ')
			   symbol = '_';
		   
		   Board[x][y] = "|_" + symbol + "_";
		   
		   if (y == gridSize-1)
			   Board[x][y] += "|";
		}
		
	}
	
	/*public void printBoard(){
		
		for (int j = 0; j < Board.length; j++){
			for (int k = 0; k < Board[j].length; k++){
				System.out.print(Board[j][k]);
			}
			System.out.println();
		}
		System.out.println();
	}*/
	
	/*public String help(){
		
		return "1. The format for the grid is nXn, i.e. 3X3" + "\n" + "2. The smallest grid is 3X3" + "\n" + "3. When entering coordinates, use the format x,y, i.e. 1,3" + "\n" + "4. x stands for row and y stands for coloumn. The least value for both is 1 and the maximum value is n (where n is that of the grid size [nXn])" + "\n" + "5. You can enter U to undo" + "\n" + "6. You can enter F to view Hall of Fame" + " \n" + "7. When prompted to exit or continue, enter Y for Yes and continue or N for No and eixt";
		
	}*/
	
	/*public void HOF(){
		
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
					
					if ((one.score != 0) || (two.score != 0)){
					output.write(x1 + one.score + "," + two.score + ",");
					output.write("\r\n");
					output.write(x2 + one.Name + "," + two.Name + ",");
					}
					else {
						output.write(x1);
						output.write("\r\n");
						output.write(x2);
					}
					
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
			System.out.println("No scores yet!");
		}
		
		return scores;
		
	}*/
	
    /*public static void main(String [] args){
    	
    	XOGame y = new XOGame();
    	String s = y.printHOF();
    	System.out.println(s);
    	// Here, I create an instance of the class using the default constructor. I only use it to call the newGame() method.
	
    }*/
	
}