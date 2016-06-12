import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class Board extends JFrame implements ActionListener{
	
	JButton [] b;
	JButton r, e, u;
	JLabel l;
	
	public Board(int s){
		
		b = new JButton[s*s];
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		p1.setBorder(BorderFactory.createEmptyBorder(-10,10,0,0));
		
		int n = 0;
		for(int i = 0; i < s; i++){
			for(int j = 0; j < s; j++){
				b[n] = new JButton("");
				gbc.ipady = 20;
				gbc.gridx = j;
				gbc.gridy = i;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				gbc.weightx = 0;
				gbc.weighty = 0;
				p1.add(b[n], gbc);
				n++;
			}
		}
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBorder(BorderFactory.createEmptyBorder(70,0,0,50));
		
		l = new JLabel("");
		p2.add(l);
		Dimension minSize = new Dimension(0, 25);
		Dimension prefSize = new Dimension(0, 25);
		Dimension maxSize = new Dimension(0, 25);
		p2.add(new Box.Filler(minSize, prefSize, maxSize));
		u = new JButton("Undo");
		u.setEnabled(false);
		p2.add(u);
		p2.add(r = new JButton("Reset"));
		p2.add(e = new JButton("End"));
		
		Container c = getContentPane();
		c.add(p1, BorderLayout.WEST);
		c.add(p2, BorderLayout.EAST);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		
		
	}
	
	public static void main(String[] args){
		
		Board B = new Board(3);
		B.pack();
		B.setSize(500,300);
		B.setLocationRelativeTo(null);
		B.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		B.setVisible(true);
		
	}
	
}