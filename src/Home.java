import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


public class Home extends JFrame implements ActionListener{
	
	JButton a, b, c, d;
	JMenuBar menuBar;
	JMenu Game, Help;
	JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
	
	public Home(){
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		a = new JButton("New Game");
		a.setMaximumSize(new Dimension(122,26));
		b = new JButton("Hall of Fame");
		b.setMaximumSize(new Dimension(122,26));
		p1.setBorder(BorderFactory.createEmptyBorder(85,50,20,10));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		c = new JButton("Help");
		c.setPreferredSize(new Dimension(122,26));
		c.setMaximumSize(new Dimension(122,26));
		d = new JButton("Exit");
		d.setPreferredSize(new Dimension(122,26));
		d.setMaximumSize(new Dimension(122,26));
		p2.setBorder(BorderFactory.createEmptyBorder(85,10,20,50));
		
		p1.add(a);
		p1.add(b);
		p2.add(c);
		p2.add(d);
		
		Container content = getContentPane();
		content.add(p1, BorderLayout.WEST);
		content.add(p2, BorderLayout.EAST);
		
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		
		menuBar = new JMenuBar();
		Game = new JMenu("Game");
		Help = new JMenu("Help");
		menuBar.add(Game);
		menuBar.add(Help);
		
		menuItem1 = new JMenuItem("New Game");
		Game.add(menuItem1);
		Game.addSeparator();
		
		menuItem2 = new JMenuItem("Scores");
		Game.add(menuItem2);
		Game.addSeparator();
		
		menuItem3 = new JMenuItem("Exit");
		Game.add(menuItem3);
		
		menuItem4 = new JMenuItem("About");
		Help.add(menuItem4);
		Help.addSeparator();
		
		menuItem5 = new JMenuItem("Rules");
		Help.add(menuItem5);
		
		content.add(menuBar, BorderLayout.PAGE_START);
		setJMenuBar(menuBar);
		
		/*Object [] p = {"3", "4"};
		String s = (String)JOptionPane.showInputDialog(this, "Choose", "OK", JOptionPane.PLAIN_MESSAGE, null, p, "3");
		System.out.println(s);*/
		// instead of p --> null, instead of "3" --> null.
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
}