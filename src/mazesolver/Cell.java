package mazesolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;



	
public class Cell extends JPanel {

		
		public Cell(){
			getPreferredSize();
			setBorder();
			
		}
		//set cell dimension
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(50, 50);
		}
		
		//set cell border
		public void setBorder() {
			Border border = new MatteBorder(1, 1, 0, 0, Color.gray);
			super.setBorder(border);
		}
		
		//set cell label - numbering the cells
		public void setLabel(int count){
			JLabel jlabel = new JLabel(Integer.toString(count));
			jlabel.setFont(new Font("Verdana", 1, 10));
			
			this.add(jlabel);
			
		}
		
		//set cell as a wall - set background to black
		public void setAsWall(){
			
			this.setBackground(Color.black);
		}
		
		//set cell as a path - set background to green
		public void setAsPath(){
			
			this.setBackground(Color.green);
		}
		//set cell as a wall - set background to red
		public void setAsEnd(){
			
			this.setBackground(Color.red);
		}
		
		
	}


