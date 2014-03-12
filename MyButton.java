

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class MyButton extends JButton implements ActionListener {
	private String text;
	private int x, y; //moving direction
	private Map m;
	private ControlPanel cop;
    public MyButton (String label, int x, int y, Map map) {
    m = map;
	setText (label);
	this.x=x;
	this.y=y;
	text = label;
	setFont (new Font("Serif", Font.ITALIC, 18));
	setBackground (Color.YELLOW);
	setForeground (Color.RED);
	setBorder (new LineBorder(Color.GREEN, 2));
	addActionListener (this);
    }
    public void addCP(ControlPanel cp){
    	cop = cp;
    }

    public void actionPerformed (ActionEvent e) {
		m.getCurrentship().speedup(x,y);
		if(m.getCurrentship()!=null){
		if (text.equals("stop")) m.getCurrentship().stop();
		if (text.equals("delete")) m.deleteCurrentship();
		if (text.equals("display")) m.getCurrentship().display();
		}
		if (text.equals("pause")) {m.pause(); cop.disAble();}
    }
}


