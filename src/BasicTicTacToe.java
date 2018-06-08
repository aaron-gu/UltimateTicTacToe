import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BasicTicTacToe extends JPanel{
	
	//private JPanel messages; // use for playing BASIC tic tac toe games
	private JPanel grid; // the JPanel where the game is played
	
	private String position; // the position of the board in the Ultimate game
	private Cell[][] cells = new Cell[3][3]; /* contains the cells */
	private String winner = ""; // "X", "O", "XO" for tie, "" for no winner
	private Color base; // default gray color
	private Color yellow = new Color(255,255,178); // highlighted yellow color
	private Color Xblue = new Color(227,223,251);
	private Color Ored = new Color(249,199,190);

	public BasicTicTacToe(){

		/* use for playing BASIC tic tac toe games
		 * 
		messages = new JPanel();
		JLabel text = new JLabel("Game time started");
		messages.add(text);
		this.add(messages);
		 */
		
		this.setOpaque(false); // so the Ultimate grid lines can show up
		base = UIManager.getColor("Panel.background");
		
		grid = new JPanel();
		this.add(grid);
		grid.setLayout(new GridLayout(3,3));
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c<3; c++) {
				cells[r][c] = new Cell();
				cells[r][c].setParent(this);
				cells[r][c].setPosition("" + r + c);
				grid.add(cells[r][c]);
			}
		}
	}

	/* Used to determine a win for the Ultimate game */
	public String getWinner() {
		return winner;
	}
	
	/* Used to assign the board's position in the Ultimate game */
	public void setPosition(String pos) {
		position = pos;
	}
	public String getPosition() {
		return position;
	}
	
	public void checkWin() {
		if(isFull()) {
			winner = "XO";
			UltimateTicTacToe.msg.setText("Board " + position + " is tied");
		}
		if(!(checkHorizontals() || checkVerticals() || checkDiagonals())) {
			winner = "";
			//grid.setBackground(base);
		}
	}
	
	/* helper methods for checkWin() */
	private boolean checkHorizontals() {
		for(int i = 0; i < 3; i++) {
			if(cells[i][0].getPlayer().equals(cells[i][1].getPlayer()) 
					&& cells[i][1].getPlayer().equals(cells[i][2].getPlayer())
					&& !cells[i][0].getPlayer().equals("")) {
				setWin(cells[i][0].getPlayer());
				return true;
			}
		}
		return false;
	}
	private boolean checkVerticals() {
		for(int i = 0; i < 3; i++) {
			if(cells[0][i].getPlayer().equals(cells[1][i].getPlayer()) 
					&& cells[1][i].getPlayer().equals(cells[2][i].getPlayer())
					&& !cells[0][i].getPlayer().equals("")) {
				setWin(cells[0][i].getPlayer());
				return true;
			}
		}
		return false;
	}
	private boolean checkDiagonals() {
		if(cells[0][0].getPlayer().equals(cells[1][1].getPlayer()) 
				&& cells[1][1].getPlayer().equals(cells[2][2].getPlayer())
				&& !cells[0][0].getPlayer().equals("")) {
			setWin(cells[0][0].getPlayer());
			return true;
		}
		if(cells[2][0].getPlayer().equals(cells[1][1].getPlayer())
				&& cells[1][1].getPlayer().equals(cells[0][2].getPlayer())
				&& !cells[2][0].getPlayer().equals("")) {
			setWin(cells[2][0].getPlayer());
			return true;
		}
		return false;
	}
	private boolean isFull() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(cells[r][c].getPlayer().equals(""))
					return false;
			}
		}
		return true;
	}
	
	public void setWin(String player) {
		winner = player;
		if(winner.equals("X")) {
			grid.setBackground(Xblue);
		}	
		else if(winner.equals("O")){
			grid.setBackground(Ored);
		}
		disableAll();
	}
	
	/* Used as a helper method for setWin() and when the Ultimate game is finished */
	public void disableAll() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				cells[r][c].nullCell();
			}
		}
	}
	
	/* Used after a board is completed, but a move needs to be undone */
	public void reenableBoard() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(cells[r][c].getPlayer().equals("")) {
					cells[r][c].resetCell();
				}
			}
		}
		checkWin(); // to see if the winner was removed
	}
	
	/* called from resetGame() */
	public void resetBoard() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				cells[r][c].resetCell();
			}
		}
		setHighlighted(false);
		winner = "";
	}
	
	/* a visual indication for when the next move is to this board */
	public void setHighlighted(boolean highlight) {
		if(highlight)
			grid.setBackground(yellow);
		else
			grid.setBackground(base);
	}
	
	
	/*
	public static void main(String[] args) {
		BasicTicTacToe b = new BasicTicTacToe();
		JFrame frame = new JFrame();
		frame.add(b);
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(200, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	*/
}
