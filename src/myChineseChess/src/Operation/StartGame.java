package Operation;

import myChessBoard.MainChessBoard.*;

public class StartGame {
	
	public static void main(String args[])
	{
		MainChessBoard myChess = new MainChessBoard();
		Thread t1 =  new Thread(myChess);
		t1.start();
	}
}
