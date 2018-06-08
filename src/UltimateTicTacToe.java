import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UltimateTicTacToe extends JPanel {

	private static final long serialVersionUID = 2L;
	
	public static int move = 0; // the number of moves elapsed
	public static BasicTicTacToe[][] boards = new BasicTicTacToe[3][3]; // contains the 9 tictactoe boards
	public static String correctBoard = ""; // which board the next move must be played in,
										   // "" means the move can be played in any board
	public static boolean gameOver = false;
	
	private JPanel messages; public static JLabel msg; // says whose turn it is and other messages
	private JPanel grid; // where the game is played
	private JPanel functions; // the undo and restart buttons
	
	public UltimateTicTacToe() {
		this.setLayout(new BorderLayout());
		messages = new JPanel();
		msg = new JLabel("Welcome to Ultimate Tic Tac Toe! X moves first on any board");
		messages.add(msg);
		this.add(messages, BorderLayout.NORTH);
		
		grid = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				super.paintComponent(g2);
				g2.setStroke(new BasicStroke(6));
				g.drawLine(10, 195, 615, 195); //horizontal lines
				g.drawLine(10, 392, 615, 392);
				g.drawLine(210, 10, 210, 575); //vertical lines
				g.drawLine(417, 10, 417, 575);
			}
		};
		grid.setLayout(new GridLayout(3,3));
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				boards[r][c] = new BasicTicTacToe();
				boards[r][c].setPosition("" + r + c);
				grid.add(boards[r][c]);
			}
		}
		this.add(grid, BorderLayout.CENTER);
		
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new UndoActionListener());
		undo.setFocusable(false);
		UndoActionListener.correctBoardList.add(""); // pad the list
		
		JButton restart = new JButton("Restart");
		restart.addActionListener(new RestartActionListener());
		restart.setFocusable(false);
		
		functions = new JPanel();
		functions.add(undo);
		functions.add(restart);
		
		this.add(functions, BorderLayout.SOUTH);
	}
	
	/* Called after each move in MoveActionListener */
	public static boolean checkWin() {
		return checkHorizontal() || checkVertical() || checkDiagonal();
	}
	
	/* Helper methods for checkWin() */
	private static boolean checkHorizontal() {
		for(int i = 0; i < 3; i++) {
			if(boards[i][0].getWinner().equals(boards[i][1].getWinner())
					&& boards[i][1].getWinner().equals(boards[i][2].getWinner())
					&& !boards[i][0].getWinner().equals("") 
					&& !boards[i][0].getWinner().equals("XO")) {
				return gameWon(i, 0);
			}
		}
		return false;
	}
	private static boolean checkVertical() {
		for(int i = 0; i < 3; i++) {
			if(boards[0][i].getWinner().equals(boards[1][i].getWinner())
					&& boards[1][i].getWinner().equals(boards[2][i].getWinner())
					&& !boards[0][i].getWinner().equals("") 
					&& !boards[0][i].getWinner().equals("XO")) {
				return gameWon(0, i);
			}
		}
		return false;
	}
	private static boolean checkDiagonal() {
		if(boards[0][0].getWinner().equals(boards[1][1].getWinner())
				&& boards[1][1].getWinner().equals(boards[2][2].getWinner())
				&& !boards[0][0].getWinner().equals("")
				&& !boards[0][0].getWinner().equals("XO")) {
			return gameWon(0, 0);
		}
		if(boards[2][0].getWinner().equals(boards[1][1].getWinner())
				&& boards[1][1].getWinner().equals(boards[0][2].getWinner())
				&& !boards[2][0].getWinner().equals("")
				&& !boards[2][0].getWinner().equals("XO")) {
			return gameWon(2, 0);
		}
		return false;
	}
	/* Helper method to null all boards when the game is won and display the winner [i, j] */
	private static boolean gameWon(int i, int j) {
		msg.setText(boards[i][j].getWinner() + " wins!");
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				boards[r][c].disableAll();
			}
		}
		gameOver = true;
		return true; //always returns true because it is called when a board is won
	}
	
	/* Sets the correctBoard and processes the previous board */
	public static void setNextBoard(String pos) {
		// gray out previous board if it's not won
		String prevBoard = correctBoard;
		if(!prevBoard.equals("")) {
			int[] prev = parseBoard(prevBoard);
			BasicTicTacToe board = boards[prev[0]][prev[1]];
			if(board.getWinner().equals("") || 
					board.getWinner().equals("XO"))
				board.setHighlighted(false);
		}
		
		// set the next correct board
		correctBoard = pos;
		int[] curr = parseBoard(correctBoard);
		checkCompleted(curr[0], curr[1]);
	}
	
	/* Helper method for setNextBoard() to adjust the correctBoard depending on if a board is completed or not */
	public static boolean checkCompleted(int r, int c) {
		if(!boards[r][c].getWinner().equals("")) {
			correctBoard = "";
			return true;
		}
		else {
			boards[r][c].setHighlighted(true);
			return false;
		}
	}
	
	public static void updateMessage() {
		if(correctBoard.equals("")) {
			msg.setText(whoseTurn() + "'s move on any board");
		}
		else {
			msg.setText(whoseTurn() + "'s move on board " + correctBoard);
		}
	}
	
	/* Called in RestartActionListener */
	public static void resetGame() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				boards[r][c].resetBoard();
			}
		}
		move = 0;
		correctBoard = "";
		msg.setText("Welcome to Ultimate Tic Tac Toe! X moves first on any board");
		gameOver = false;
		UndoActionListener.movesList.clear();
		UndoActionListener.correctBoardList.clear();
		UndoActionListener.correctBoardList.add(""); // pad the list
	}
	
	/* Helper static method determining whose turn it is */
	public static String whoseTurn() {
		if(move%2 == 0)
			return "X";
		else
			return "O";
	}
	
	/* Helper static method for getting the board position from the string */
	public static int[] parseBoard(String board) {
		int[] pos = new int[2];
		pos[0] = Integer.parseInt(board.substring(0, 1));
		pos[1] = Integer.parseInt(board.substring(1, 2));
		return pos;
	}
	
	
	
	/* Driver */
	public static void main(String[] args) {
		UltimateTicTacToe game = new UltimateTicTacToe();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(625, 680);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}

}
