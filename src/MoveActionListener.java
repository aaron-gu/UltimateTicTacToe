import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/* Action listener for the cell's button */
public class MoveActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// setting the button to its correct state
		JButton press = (JButton) e.getSource();
		BasicTicTacToe board = ((Cell) press.getParent()).getParent();
		if(!UltimateTicTacToe.correctBoard.equals("") && 
				!board.getPosition().equals(UltimateTicTacToe.correctBoard)) {
			UltimateTicTacToe.msg.setText("That's not the right board to move to!");
			return;
		}
		
		//processing the pressed button
		press.setEnabled(false);
		press.setText(UltimateTicTacToe.whoseTurn());
		UndoActionListener.movesList.add((Cell) press.getParent());
		UndoActionListener.correctBoardList.add(UltimateTicTacToe.correctBoard);
		
		// checking for a board's win
		board.checkWin();
		// checking for the game's win
		if(UltimateTicTacToe.checkWin()) {
			UltimateTicTacToe.msg.setText(UltimateTicTacToe.whoseTurn() + " wins!");
			return;
		}
		
		// sending the next player to the right location
		UltimateTicTacToe.setNextBoard(((Cell) press.getParent()).getPosition());
		
		// all actions finished, time for the next move
		UltimateTicTacToe.move++;
		UltimateTicTacToe.updateMessage();
	}
}