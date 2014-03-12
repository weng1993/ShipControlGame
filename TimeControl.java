import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TimeControl extends JPanel implements AdjustmentListener{
	static final int INIT_DELAY = 150;
	static final int MAX_DELAY = 300;
	static final int MIN_DELAY = 20;
	static final int EXTENT = 20;
	
	Timer timer;
	
	public TimeControl(Timer tim){
		timer = tim;
		setLayout(new BorderLayout());
		JScrollBar scroll = new JScrollBar
				(JScrollBar.HORIZONTAL, INIT_DELAY, EXTENT, MIN_DELAY, MAX_DELAY);
		scroll.addAdjustmentListener(this);
		add(scroll);
	}
	public void adjustmentValueChanged(AdjustmentEvent e){
		timer.setDelay(300-e.getValue());
	}
}
