import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class RestartActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?", 
				"Select an Option", JOptionPane.YES_NO_OPTION); 
		if(option == 0) {
			UltimateTicTacToe.resetGame();
		}
	}
}