package mazesolver;



import java.awt.BorderLayout;

import javax.swing.JFrame;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Testing");
		
		Input in = new Input();
		int row = in.getRow();
		int col = in.getColumn();

		MazeBoard ss = new MazeBoard(row,col);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(ss);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		String a = in.getcells();
		
		String[] strnum= a.split(",");
		int[] wallcells = new int[strnum.length];
		
		for(int i=0;i<strnum.length;i++){
			wallcells[i]=Integer.parseInt(strnum[i]);
		}
		
		frame.remove(ss);
		frame.repaint();
		
		ss.AddWall(wallcells);
		
		frame.add(ss);
		frame.repaint();

	}

}
