import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UltimateTicTacToe {
	
	public int moves = 1; // true is X, false is O, X moves first
	public boolean[][] xo = new boolean[3][3]; //helps check win
	public JButton[][] buttonMatrix = new JButton[3][3];
	
	JFrame frame = new JFrame();
	
	
	public UltimateTicTacToe() {
		createUltimateBoard();
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	public void createUltimateBoard() {
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
			createBasicBoard();
		
		frame.add(panel);
	}
	
	public void createBasicBoard(){
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawLine(50, 0, 50, 300);
				g.drawLine(100, 0, 100, 300);
				g.drawLine(0, 50, 300, 50);
				g.drawLine(0, 100, 300, 100);
			}
		};
		panel.setLayout(new GridLayout(3,3));
		for(int r = 0; r<3; r++) {
			for(int c = 0; c<3; c++) {
				xo[r][c] = true;
				JButton matrixAdd = new JButton();
				matrixAdd.setPreferredSize(new Dimension(50,50));
				matrixAdd.addActionListener(new MoveActionListener());
				buttonMatrix[r][c] = matrixAdd;
				panel.add(buttonMatrix[r][c]);
			}
		}
	}
	
	public class MoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setEnabled(false);
			if(moves%2 == 1) {
				((JButton) e.getSource()).setText("X");
				moves++;

			}
			else {
				((JButton) e.getSource()).setText("O");
				moves++;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UltimateTicTacToe();
	}

}
