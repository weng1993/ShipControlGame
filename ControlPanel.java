import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
import javax.swing.border.*;

public class ControlPanel extends JPanel
							implements ActionListener,
							AdjustmentListener{
	private MyButton up, down, left, right, stop, delete, display,pause;
	private JPanel buttons;
	private Map m;
	private JComboBox allShips;
	private boolean disabled = false;
    public ControlPanel (Map map) {
    	m = map;
		// Settings
		setLayout (new BorderLayout());
		setBorder (new LineBorder(Color.BLUE));
		
		//add type Box
		String[] s = {"Titanic","Submarine","Kayak"};
		allShips = new JComboBox(s);
		allShips.addActionListener(this);
		add(allShips, BorderLayout.NORTH);
		

		
		//add zoombar
		JScrollBar zoombar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 30, 10, 100);
		add(zoombar, BorderLayout.CENTER);
		zoombar.addAdjustmentListener(this);
		
		//add buttons
		buttons = new JPanel(new GridLayout(2,4));
		up = new MyButton("up", 0, -3, m);
		buttons.add(up);
		down = new MyButton("down", 0, 3, m);
		buttons.add(down);
		left = new MyButton("left",  -3, 0, m);
		buttons.add(left);
		right = new MyButton("right",  3, 0, m);
		buttons.add(right);
		stop = new MyButton("stop",  0, 0, m);
		buttons.add(stop);
		delete = new MyButton("delete", 0, 0, m);
		buttons.add(delete);
		display = new MyButton("display",  0, 0, m);
		buttons.add(display);
		pause = new MyButton("pause",  0, 0, m);
		pause.addCP(this);
		buttons.add(pause);
		add(buttons,BorderLayout.SOUTH);

    }
    public void disAble(){
    	up.setEnabled(disabled);
    	down.setEnabled(disabled);
    	left.setEnabled(disabled);
    	right.setEnabled(disabled);
    	stop.setEnabled(disabled);
    	disabled = !disabled;
    }

	public void adjustmentValueChanged(AdjustmentEvent e){
		Ship.setZoom((double)((double)e.getValue() /30));
		System.out.println(Ship.zoom);
	}
	
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        m.setCurrentType(cb.getSelectedIndex()) ;
    }
    
}