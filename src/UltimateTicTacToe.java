import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UltimateTicTacToe {
	
	public int moves = 1; // true is X, false is O, X moves first
	public boolean[][] xo = new boolean[3][3]; //helps check win
	public JButton[][] buttonMatrix = new JButton[3][3];
	
	JFrame frame = new JFrame();
	JPanel ultimatePanel = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(10));
			g2.drawLine(150, 0, 150, 450);
			g2.drawLine(300, 0, 300, 450);
			g2.drawLine(0, 150, 450, 150);
			g2.drawLine(0, 300, 450, 300);
		}
	};
	
	JPanel guiPanel;
	JLabel turn;
	
	JButton lastPressed;
	
	public UltimateTicTacToe() {
		createGUIBoard();
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(450, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	
	public void createGUIBoard() {
		guiPanel = new JPanel();
		guiPanel.setLayout(new BorderLayout());
		JPanel movePanel = new JPanel(); //panel to center the JLabel
		turn = new JLabel("X's Move");
		movePanel.add(turn);
		guiPanel.add(movePanel, BorderLayout.NORTH);
		createUltimateBoard();
		frame.add(guiPanel, BorderLayout.CENTER);
		JButton undo = new JButton("Undo");
		undo.addActionListener(new UndoActionListener());
		guiPanel.add(undo, BorderLayout.SOUTH);
	}
	
	public void createUltimateBoard() {
		ultimatePanel.setLayout(new GridLayout(3,3));
		for(int i = 0; i<9; i++)
			createBasicBoard();
		guiPanel.add(ultimatePanel);
	}
	
	public void createBasicBoard(){
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) { //resets the stroke from the previous graphics2D
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2));
				g2.drawLine(50, 0, 50, 300);
				g2.drawLine(100, 0, 100, 300);
				g2.drawLine(0, 50, 300, 50);
				g2.drawLine(0, 100, 300, 100);
			}
		};
		panel.setLayout(new GridLayout(3,3));
		for(int r = 0; r<3; r++) {
			for(int c = 0; c<3; c++) {
				xo[r][c] = true;
				JButton matrixAdd = new JButton();
				matrixAdd.setPreferredSize(new Dimension(35, 35));
				matrixAdd.addActionListener(new MoveActionListener());
				buttonMatrix[r][c] = matrixAdd;
				panel.add(buttonMatrix[r][c]);
			}
		}
		ultimatePanel.add(panel);
	}
	
	public class MoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setEnabled(false);
			if(moves%2 == 1) {
				((JButton) e.getSource()).setText("X");
				turn.setText("O's Move");
				moves++;

			}
			else {
				((JButton) e.getSource()).setText("O");
				turn.setText("X's Move");
				moves++;
			}
			lastPressed = (JButton) e.getSource();
		}
	}
	
	public class UndoActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			lastPressed.setEnabled(true);
			lastPressed.setText("");
			moves--;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UltimateTicTacToe();
	}

}
