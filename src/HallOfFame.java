import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class HallOfFame extends JFrame implements ActionListener{
	
	JTextArea t;
	JButton b, c;
	
	public HallOfFame(){
		
		t = new JTextArea(0, 20);
		t.setEditable(false);
		
		JScrollPane sp = new JScrollPane(t);
		
		b = new JButton("Back");
		c = new JButton("Clear");
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p2.add(Box.createHorizontalGlue());
		p2.add(b);
		p2.add(c);
		
		Container c = getContentPane();
		c.add(sp, BorderLayout.WEST);
		c.add(p2, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		
		
	}
	
	public static void main(String[] args) {
		
		HallOfFame h = new HallOfFame();
		h.pack();
		h.setSize(400,210);
		h.setLocationRelativeTo(null);
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setVisible(true);
		
	}

}
