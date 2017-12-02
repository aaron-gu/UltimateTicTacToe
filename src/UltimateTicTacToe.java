import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UltimateTicTacToe {

	public UltimateTicTacToe() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		for(int i = 0; i<9; i++)
			panel.add(new BasicTicTacToe());
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UltimateTicTacToe();
	}

}
