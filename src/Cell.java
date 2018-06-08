import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell extends JPanel {
	/**
	 * Cell is-a JPanel, which contains a JButton
	 */
	private static final long serialVersionUID = 10L;
	
	private String position;
	private JButton press;
	BasicTicTacToe parent;
	
	public Cell() {
		this.setOpaque(false); // so the Basic board color can appear
		
		press = new JButton();
		press.setText("");
		press.setPreferredSize(new Dimension(50,50));
		press.addActionListener(new MoveActionListener());
		press.setFocusPainted(false); // disables the highlighted border
		this.add(press);
	}

	public void setPlayer(int player) {
		if(player == 0)
			press.setText("X");
		else
			press.setText("O");
		press.setEnabled(false);
	}
		
	public String getPlayer() {
		return press.getText();
	}
	
	public void setPosition(String pos) {
		position = pos;
	}
	public String getPosition() {
		return position;
	}
	
	/* Used for accessing the board that the cell is in */
	public BasicTicTacToe getParent() {
		return parent;
	}
	public void setParent(BasicTicTacToe parent) {
		this.parent = parent;
	}
	
	/* Used for resetting the cell on Undo command */
	public void resetCell() {
		press.setEnabled(true);
		press.setText("");
		press.repaint();
	}
	
	/* Used for nulling cells after a board has been won */
	public void nullCell() {
		press.setEnabled(false);
	}

	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Cell myCell = new Cell();
		frame.add(myCell);
		frame.setVisible(true);
		frame.setTitle("Ultimate Tic-Tac-Toe");
		frame.setSize(450, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
	}
	*/
}
