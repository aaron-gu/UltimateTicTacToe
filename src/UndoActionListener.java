import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UndoActionListener implements ActionListener{
	public static ArrayList<Cell> movesList = new ArrayList<Cell>();
	public static ArrayList<String> correctBoardList = new ArrayList<String>();
	
	public void actionPerformed(ActionEvent e) {
		if(UltimateTicTacToe.gameOver)
			return;
		
		if(movesList.size() == 0) {
			UltimateTicTacToe.msg.setText("There are no moves to undo!");
			return;
		}
		
		// updating move tracker lists
		Cell toReset = movesList.remove(movesList.size()-1);
		toReset.resetCell();
		toReset.getParent().reenableBoard();
		toReset.getParent().setHighlighted(false);
		
		// updating correctBoard
		if(!UltimateTicTacToe.correctBoard.equals("")) {
			int[] prev = UltimateTicTacToe.parseBoard(UltimateTicTacToe.correctBoard);
			UltimateTicTacToe.boards[prev[0]][prev[1]].setHighlighted(false);
		}
		UltimateTicTacToe.correctBoard = correctBoardList.remove(correctBoardList.size()-1);
		if(!UltimateTicTacToe.correctBoard.equals("")) {
			int[] curr = UltimateTicTacToe.parseBoard(UltimateTicTacToe.correctBoard);
			UltimateTicTacToe.boards[curr[0]][curr[1]].setHighlighted(true);
		}
		
		// updating globally relevant fields
		UltimateTicTacToe.move--;
		UltimateTicTacToe.updateMessage();
	}
}