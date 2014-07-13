package mazesolver;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MazeHandler {
	private JFrame frame;
	private MazeBoard mazeboard;
	private PathGen path;
	private int[][] wallcells;
	private int[][] startendcells;
	private int row;
	private int col;
	private int delay;

	// constructor
	public MazeHandler(int row, int col) {
		this.row = row;
		this.col = col;

		// create a frame and maze grid
		this.frame = new JFrame("Maze Solver");
		this.mazeboard = new MazeBoard(row, col);

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());
		this.frame.add(mazeboard);
		this.frame.pack();
		this.frame.setVisible(true);
	}

	// set the wall according to the user input
	public void SetWallInMaze(String walls) {
		String[] strnum = walls.split(",");
		this.wallcells = new int[strnum.length][2];

		// convert to integer values
		for (int i = 0; i < strnum.length; i++) {
			wallcells[i][0] = Integer.parseInt(strnum[i]);
		}

		RemoveCurrentMaze();

		// get wall coordinates
		wallcells = getCordinates(wallcells, col);
		// add walls
		mazeboard.AddWall(wallcells);

		RefreshCurrentMaze();

	}

	// get the solved maze
	public void GetMazePath(int start, int end) {
		// get starting and end cells
		this.startendcells = new int[2][2];
		this.startendcells[0][0] = start;
		this.startendcells[1][0] = end;

		// get coordinates of them
		this.startendcells = getCordinates(this.startendcells, col);


		// create a maze in order to use in PathGen class
		int[][] maze = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// add free space here - free space-0
				maze[i][j] = 0;
				// add walls here - wall - 1
				for (int k = 0; k < wallcells.length; k++) {
					if (wallcells[k][0] == i && wallcells[k][1] == j)
						maze[i][j] = 1;
				}

			}

		}

		// create a pathGen instance
		this.path = new PathGen(maze);

		// do a test here - just print the solved maze
		// path.Testrun(this.startendcells);

	}

	// Animate the solved path
	public void AnimatePath() {
		ArrayList<String> pathcells = new ArrayList<String>();
		pathcells = path.getPathNodes(startendcells);

		// run separate thread here in order to animate the path
		try {

			for (int i = 0; i < pathcells.size(); i++) {
				String[] aa = pathcells.get(i).split(" ");
				int r = Integer.parseInt(aa[0]);
				int c = Integer.parseInt(aa[1]);

				RemoveCurrentMaze() ;
				mazeboard.AddPathCells(r, c);
				mazeboard.AddEndCell(startendcells[1][0], startendcells[1][1]);
				RefreshCurrentMaze();
				// thread sleep for delay milliseconds
				Thread.sleep(this.delay * 500);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void RemoveCurrentMaze() {
		// remove the current grid
		this.frame.remove(mazeboard);
		this.frame.repaint();
	}

	private void RefreshCurrentMaze() {
		// add the changed in grid to frame
		this.frame.add(mazeboard);
		this.frame.repaint();

	}

	// get coordinate of the cell
	private static int[][] getCordinates(int[][] wallcells, int numberofcol) {
		int row, col;
		int[][] wallcell = new int[wallcells.length][2];

		for (int i = 0; i < wallcells.length; i++) {

			row = (int) ((wallcells[i][0] - 1) / (numberofcol));
			col = ((wallcells[i][0] - 1) % (numberofcol));
			// System.out.println(row+" "+col);
			wallcell[i][0] = row;
			wallcell[i][1] = col;

		}
		return wallcell;

	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @param d
	 *            the delay to set
	 */
	public void setDelay(int d) {
		this.delay = d;
	}

}
