import java.awt. *;
import javax.swing. *;
import javax.swing.BoxLayout;
public class State extends JPanel{
	private String Player= "Vincent";
	private int score=0;
	private JLabel scorePanel;
	public State (){
		this.setLayout(new GridLayout(44,10));
		add(new JLabel("Player: "));
		add(new JLabel(Player));
		add(new JSeparator());
		add(new JLabel("Score: "));
		scorePanel = new JLabel(Integer.toString(score));
		add(scorePanel);
		
		
	}
	public void updateScore(){
		score ++;
		scorePanel.setText(Integer.toString(score));
	}
	
	
}
