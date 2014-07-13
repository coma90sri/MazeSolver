package mazesolver;

public class MazeDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Input user = new Input();
		int row = user.getRow();
		int col = user.getColumn();
		
		MazeHandler mymazehandler = new MazeHandler(row,col);
		
		//get wall cells from user
		String walls = user.getWallcells();
		
		//set wall in maze
		mymazehandler.SetWallInMaze(walls);
		
		//get starting and ending cells from user
		int start = user.getStartingCell();
		int end = user.getEndCell();
		
		//get maze path
		mymazehandler.GetMazePath(start, end);
		
		//set delay time
		mymazehandler.setDelay(1);
		
		//animate maze path
		mymazehandler.AnimatePath();
		
		
	}

}
