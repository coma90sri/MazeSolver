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
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(50, 50);
		}
		
		public void setBorder() {
			Border border = new MatteBorder(1, 1, 0, 0, Color.gray);
			super.setBorder(border);
		}
		
		
		public void setLabel(int count){
			JLabel jlabel = new JLabel(Integer.toString(count));
			jlabel.setFont(new Font("Verdana", 1, 10));
			
			this.add(jlabel);
			
		}
		
		
		public void setAsWall(){
			
			this.setBackground(Color.black);
		}
		
		public void setAsPath(){
			
			this.setBackground(Color.green);
		}
		
		public void setAsEnd(){
			
			this.setBackground(Color.red);
		}
		
		
	}


