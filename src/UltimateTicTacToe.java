import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UltimateTicTacToe {
	
	public UltimateTicTacToe() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g2) {
				//Graphics2D g2 = (Graphics2D) g;
				//g2.setStroke(new BasicStroke(10));
				g2.drawLine(0, 0, 300, 300);
				g2.drawLine(170, 0, 170, 450);
				g2.drawLine(300, 0, 300, 450);
				g2.drawLine(0, 150, 450, 150);
				g2.drawLine(0, 300, 450, 300);
			}
		};
		panel.setLayout(new GridLayout(3,3));
		for(int i = 0; i<9; i++)
			panel.add(new BasicTicTacToe());
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UltimateTicTacToe();
	}

}
