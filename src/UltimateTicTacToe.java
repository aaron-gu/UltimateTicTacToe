import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class UltimateTicTacToe {

	public int moves = 0; // true is X, false is O, X moves first
	public boolean[][] xo = new boolean[3][3]; //helps check win
	public JButton[][] buttonMatrix = new JButton[3][3];
	
	//panel that fills the screen and has the thick board lines
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
	
	//the panel that fills the frame
	JPanel guiPanel;
	
	//label for whose turn it is (shows up at top of GUI)
	JLabel turn;
	
	//keeps track of the last pressed button in order to undo
	JButton lastPressed = null;

	//color to indicate the last move
	Color lightyellow = new Color(252, 255, 131);

	
	
	/*
	 * constructor for the frame
	 */
	public UltimateTicTacToe() {
		JFrame frame = new JFrame();
		createGUIBoard();
		frame.add(guiPanel);
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
		JButton undo = new JButton("Undo");
		undo.addActionListener(new UndoActionListener());
		guiPanel.add(undo, BorderLayout.SOUTH);
		
	}

	public void createUltimateBoard() {
		ultimatePanel.setLayout(new GridLayout(3,3));
		for(int i = 0; i<9; i++)
			createBasicBoard();
		guiPanel.add(ultimatePanel, BorderLayout.CENTER);
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

				//create button size and action listener
				JButton matrixAdd = new JButton();
				matrixAdd.setPreferredSize(new Dimension(35, 35));
				matrixAdd.addActionListener(new MoveActionListener());

				//makes it so the board doesn't look like it has buttons
				matrixAdd.setBorder(null);

				//turn off the focus listener
				matrixAdd.setFocusable(false);
				
				//add to the matrix
				buttonMatrix[r][c] = matrixAdd;

				//add to the panel
				panel.add(buttonMatrix[r][c]);
			}
		}
		ultimatePanel.add(panel);
	}

	/*
	 * Action Listener for every button pressed / move made
	 */
	public class MoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//changing the previously pressed button back to gray
			if(lastPressed!=null) {
				lastPressed.setFocusPainted(false);
				lastPressed.setContentAreaFilled(false);
				lastPressed.setBackground(null);
				lastPressed.setBorder(null);
			}

			JButton pressed = (JButton) e.getSource();
			//cannot be changed anymore (unless undo)
			pressed.setEnabled(false);
			//the whole button will be yellow
			pressed.setOpaque(true);
			pressed.setBackground(lightyellow);
			//keeps it so there is no visible button
			pressed.setBorder(null);
			

			//changing the button and turn counter
			moves++;
			if(moves%2 == 1) {
				pressed.setText("X");
				turn.setText("O's Move");
			}
			else {
				pressed.setText("O");
				turn.setText("X's Move");
			}
			lastPressed = pressed;
		}
	}

	/*
	 * Action Listener for the Undo button
	 */
	public class UndoActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//resetting the button to its original state
			if(lastPressed != null) {
				lastPressed.setEnabled(true);
				lastPressed.setBackground(null);
				lastPressed.setText("");
				
				//going back one move
				moves--;
				//changing the turn counter
				if(moves%2 == 1)
					turn.setText("O's Move");
				else
					turn.setText("X's Move");
				
				//undo cannot do anything until another move is made
				lastPressed = null;
			}
		}
	}

	public static void main(String[] args) {
		// generate the game
		new UltimateTicTacToe();
	}

}
