package mazesolver;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class MazeBoard extends JPanel {

	private int inputRow;
	private int inputColumn;

	private GridBagLayout Mazegrid;
	private GridBagConstraints gbc;
	private CellGrid maze_cells;
	private int margin_cells;

	// constructor
	public MazeBoard(int row, int col) {

		this.inputRow = row;
		this.inputColumn = col;

		this.Mazegrid = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		this.maze_cells = new CellGrid(inputRow, inputColumn);
		this.margin_cells = this.maze_cells.getMarginCells();

		Init();

	}

	// update mazeboard
	public void Init() {
		setLayout(Mazegrid);

		for (int row = 0; row < inputRow + 2 * margin_cells; row++) {
			for (int col = 0; col < inputColumn + 2 * margin_cells; col++) {

				gbc.gridx = col;
				gbc.gridy = row;

				add(maze_cells.ReturnGridCell(row, col), gbc);

			}
		}

	}

	// add walls to mazeboard
	public void AddWall(int[][] wallcells) {
		int row, col;
		for (int i = 0; i < wallcells.length; i++) {
			row = wallcells[i][0];
			col = wallcells[i][1];
			// System.out.println(row+" "+col);
			maze_cells.setCellAs(row + 1, col + 1, "wall");
		}
		Init();

	}
	//add pathcell in mazeboard
	public void AddPathCells(int row, int col) {

		maze_cells.setCellAs(row + 1, col + 1, "path");
		Init();
	}
	//add endcell in mazeboard
	public void AddEndCell(int row, int col) {

		maze_cells.setCellAs(row + 1, col + 1, "end");
		Init();
	}
}
