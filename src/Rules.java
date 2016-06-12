import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class Rules extends JFrame implements ActionListener{
	
	JLabel rules;
	JButton b;
	
	public Rules(){
		
	JPanel p1 = new JPanel();
	p1.setLayout(new FlowLayout());
	rules = new JLabel("<html>1- Player 1 is X.<br><br>2- You can use the 'Undo' button only once.<br><br>3- The player who inserts (n) adjacent symbols vertically, <br> horizontally or diagonally wins. Where (n) is the size.</html>");      // needs to be edited
	rules.setFont(rules.getFont().deriveFont(15.0f));
	p1.add(rules);
	
	b = new JButton("Back");
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
	p2.add(Box.createHorizontalGlue());
	p2.add(b);
	
	Container c = getContentPane();
	c.add(p1, BorderLayout.WEST);
	c.add(p2, BorderLayout.SOUTH);
	
	}
	
	public void actionPerformed(ActionEvent e){
		
		
		
	}
	
	public static void main(String[] args) {
		
		Rules B = new Rules();
		B.pack();
		B.setSize(500,200);
		B.setLocationRelativeTo(null);
		B.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		B.setVisible(true);
		
	}

}