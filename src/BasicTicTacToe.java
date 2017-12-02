import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicTicTacToe extends JPanel{
	
	public boolean[][] xo = new boolean[3][3]; //helps check win
	public JButton[][] buttonMatrix = new JButton[3][3];
	public boolean turn = true; // true is X, false is O, X moves first
	
	public BasicTicTacToe(){
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
		
		this.add(panel);
	}
	
	public class MoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setEnabled(false);
			if(turn == true) {
				((JButton) e.getSource()).setText("X");
				turn = false;
				
			}
			else {
				((JButton) e.getSource()).setText("O");
				turn = true;
			}
		}
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new BasicTicTacToe();
//	}

}
