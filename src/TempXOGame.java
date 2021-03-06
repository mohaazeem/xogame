import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TempXOGame{

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
	
	public TempXOGame(){
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
	
	public void newGame(){
		
		if (round == 0) {
		
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
		
		}
		
		String s1;
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
		this.gridSize = size;		
		
		System.out.println("Game Started!");
		this.board();
		this.printBoard();
		this.start();
		
	
}
	public void start(){    // in this method, the user is asked to enter the coordinates of their next input.
		
		
		while (i < (gridSize*gridSize)){	
			
			System.out.println("Enter co-ordinates in the format: x,y or U for undo or E to exit");
			
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
			
			this.insert(x,y-1);
			this.board();
			this.printBoard();
			if (this.check())
				break;
			
			this.start();          // The coordinates() method is called to allow the players to insert again.
			
		}
		
		System.out.println("Game Ended!");     // This line will be reached when i is equal to gridSize^2, which indicates that the grid is full and the game ended as a draw.
		System.out.println("Do you want to start a new game? (Y/N)");
		

		while (true){
			
			String ss = input.nextLine();
						
		if (ss.equals("Y")){
			(new TempXOGame()).newGame();         // After the game has ended, a new game is initialized.
		}
		    else if (ss.equals("N")) {
			System.out.println("Bye!");
			round = 0;
			this.HOF();
			System.exit(0);
			
		   }
		else System.out.println("Wrong Format! Please enter Y or N!");
		
		}
	}
	
	public void insert(int x, int y){
		last = "" + x + y;	
		if ((Board[x][y].charAt(2) != 'X') && (Board [x][y].charAt(2) != 'O')){        // The cell is empty.
			
		if (i%2 == 0){                        // Player 1 will always use the 'X', whereas Player 2 will use the 'O'. So as i starts from 0, every time i is even an 'X' will be inserted, and for the odd values of i an 'O' will be inserted.
		i++;
		symbol = 'X';
		}
		else if (i%2 != 0){
		i++;
		symbol = 'O';
		}
		}
		
		else {
			System.out.println("Position occupied!");
			symbol = Board[x][y].charAt(2);
		}
	}
	
	public boolean check(){
		
		if (this.row() || this.column() || this.diagonalRight() || this.diagonalLeft()){         // After every insertion, the algorithm checks if a player has won.
			if (winner == 'X'){
			System.out.println(one.Name + " has won!");
			one.score++;
			}
			else if (winner == 'O'){
			System.out.println(two.Name + " has won!");
			two.score++;
			}
			
			return true;
			
	}
		
		else return false;
		
	}
	
	public void undo(){
		if (i == 0)
			System.out.println("Nothing to undo!");
		else {
		symbol = ' ';
		i--;    // I decrement the i as I previously mentioned that it indicates the number of cells in use. So an undo will free a new cell.
		}
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
	
	public void printBoard(){
		
		for (int j = 0; j < Board.length; j++){
			for (int k = 0; k < Board[j].length; k++){
				System.out.print(Board[j][k]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public String help(){
		
		return "1. The format for the grid is nXn, i.e. 3X3" + "\n" + "2. The smallest grid is 3X3" + "\n" + "3. When entering coordinates, use the format x,y, i.e. 1,3" + "\n" + "4. x stands for row and y stands for coloumn. The least value for both is 1 and the maximum value is n (where n is that of the grid size [nXn])" + "\n" + "5. You can enter U to undo" + "\n" + "6. You can enter F to view Hall of Fame" + " \n" + "7. When prompted to exit or continue, enter Y for Yes and continue or N for No and eixt";
		
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
	
	public void printHOF(){          // This method prints the scores when invoked. So it is responsible for 'reading' from a file.
		
		String fileName = "HallOfFame.txt";    // You instantiate a variable (here, its name is fileName) and the right hand side is the name of the file saved on the disk. It takes the form of a String. Similar to what you do in the method above.
		File file = new File(fileName);      // Here, you create an instance from the class 'File" which takes as a parameter the String you instantiated above. It's like it tells java that the String you created is a file. Again, similar to the steps in the other method.
		try {
			Scanner in = new Scanner(file);       // As this method is responsible for reading from a file, it should use a 'Scanner', and the parameter here is the File you created. The 'try' here tries reading from the file, this handles the case where the files still doesn't exist on your drive, so you 'catch' this case. The body for 'catch' is at the end of this method.
			String s1 = in.nextLine();     // In my implementation, I make a '.txt' file where the first line contains numbers (scores), and the second line right below it contains names (players). And each name is actually right below its score. In both lines, the numbers nad the names are separated by commas.
			String s2 = in.nextLine();     // The 'in.nextLine()' is like a pointer, I specify which line I want to store in the String.
			
			String [] S1 = s1.split(",");    // Here, I create two arrays of Strings, one to store the scores in, and the other for the names. The '.split(",")' method is called to indicate that each cell would contain the string preceding every comma.
			String [] S2 = s2.split(",");
			
			for (int i = 0; i < S1.length; i++){     // I created the two arrays in order to sort the scores descendingly, using this 'for' loop.
				int max = i;
				for (int j = i; j < S1.length; j++){
					
					if (Integer.parseInt(S1[j]) > Integer.parseInt(S1[max]))
						max = j;
				}
				
				String temp = S1[i];        // As I know that each cell in the first string contains the score corresponding to the player in the same cell in the second string, that is, cell 0 for example in S1 has '4', and cell 0 in S2 has 'Mohamed', then I know that Mohamed's score is 4.
				S1[i] = S1[max];        // So, the swapping takes place twice.
				S1[max] = temp;
				
				temp = S2[i];
				S2[i] = S2[max];
				S2[max] = temp;
				
			}
			in.close();         // This is a method called on the variable of type 'Scanner' to close the stream.
			for (int k = 0; k < S1.length; k++){       // This 'for' loop just prints the name of every player and the score next to it.
				System.out.println(S2[k] + ": " + S1[k]);
			}
			
		} catch (FileNotFoundException e) {    // This is the catch body, take as a parameter the type of 'Exception', and gives it the identifier 'e' (or whatever you like).
			System.out.println("No scores yet!");    // Here, whenever the Scanner tries to read from a file that doesn't really exist, it prints out that there are still no scores.
		}
		
	}
	
    public static void main(String [] args){
    	
	    (new TempXOGame()).newGame();           // Here, I create an instance of the class using the default constructor. I only use it to call the newGame() method.
	
    }
	
}