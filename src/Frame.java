
import javax.swing.*;
import java.awt.*;

public class Frame
{
	public static void main(String [] args)
	{
	/*	JFrame frame = new JFrame("FrameDemo");
		// frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();	
		frame.setVisible(true);
	*/
	
	
	JFrame frame = new JFrame("FrameDemo");
	/*JOptionPane.showMessageDialog(frame,
    "Eggs are not supposed to be green.");
	*/
	
	Object[] options = {"Yes, please",
                    "No, thanks",
                    "No eggs, no ham!"};
	int n = JOptionPane.showOptionDialog(frame,
    "Cairo runners? "
    + "with that ham?",
    "A Silly Question",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options[2]);

	}
}