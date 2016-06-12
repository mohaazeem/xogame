import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class test extends JFrame implements ActionListener{

	JButton [] b;
	static JButton B;
	static int x;
	static int y;
	static boolean flag1 = false;
	static boolean flag2 = false;
	char symbol;
	
	JTextField t;
	
	public test(){
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3,3,-10,-10));
		
		b = new JButton[9];
		for (int i = 0; i < b.length; i++){
			p1.add(b[i] = new JButton(""));
		}
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(t = new JTextField(10));
		t.setHorizontalAlignment(t.LEFT);
		t.setEditable(false);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout());
		
		p.add(p1, BorderLayout.NORTH);
		p.add(p2, BorderLayout.SOUTH);
		
		this.add(p);
		
		for (int i = 0; i < b.length; i++){
			b[i].addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		B = (JButton)(e.getSource());
	    String s = (String)JOptionPane.showInputDialog(this, "Please enter Player 1's name", "Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
	    System.out.println(s);
		B.setEnabled(false);
		int i = 0;
		outerloop:
		for (x = 1; x <= 3; x++){
			for (y = 1; y <= 3; y++){
				if (B.equals(b[i]))
					break outerloop;
				i++;
			}
			
		}
		flag1 = true;
	}
	
	public static String getC(){
		return "" + x + "," + y;
	}
	
	public static void main(String[] args) {
        test Test = new test();
		Test.pack();
		Test.setLocationRelativeTo(null);
		Test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Test.setVisible(true);
	}

}