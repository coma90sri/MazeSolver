package mazesolver;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Testing");

		Input in = new Input();
		int row = in.getRow();
		int col = in.getColumn();

		MazeBoard ss = new MazeBoard(row, col);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(ss);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		String a = in.getcells();

		String[] strnum = a.split(",");
		int[][] wallcells = new int[strnum.length][2];

		for (int i = 0; i < strnum.length; i++) {
			wallcells[i][0] = Integer.parseInt(strnum[i]);
		}

		frame.remove(ss);
		frame.repaint();

		wallcells = getCordinates(wallcells, col);

		ss.AddWall(wallcells);

		frame.add(ss);
		frame.repaint();

		int b = in.getStartingCell();
		int c = in.getEndCell();
		
		int[][] startendcells = new int[2][2];
		startendcells[0][0]=b;
		startendcells[1][0]=c;
		
		startendcells=getCordinates(startendcells, col);

		int[][] maze = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				maze[i][j] = 0;

				for (int k = 0; k < wallcells.length; k++) {
					if(wallcells[k][0]==i && wallcells[k][1]==j)
						maze[i][j]=1;
				}

			}

		}

		PathGen path = new PathGen(maze);
		path.Testrun(startendcells);

		frame.remove(ss);
		frame.repaint();

		ss.AddWall(wallcells);

		frame.add(ss);
		frame.repaint();

	}

	public static int[][] getCordinates(int[][] wallcells, int numbeofcol) {
		int row, col;
		int[][] wallcell = new int[wallcells.length][2];

		for (int i = 0; i < wallcells.length; i++) {

			row = (int) ((wallcells[i][0] - 1) / (numbeofcol));
			col = ((wallcells[i][0] - 1) % (numbeofcol));
			// System.out.println(row+" "+col);
			wallcell[i][0] = row;
			wallcell[i][1] = col;

		}
		return wallcell;

	}

}
