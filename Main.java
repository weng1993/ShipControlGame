import java.awt.*;

import javax.swing.*;

public class Main extends JFrame {
    public static void main (String [] args) {
	new Main ();
    }

    public Main () {
	// Window setup
     setSize (1300, 700);
	 Container content = getContentPane();
	 content.setLayout(new BorderLayout());
	 setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	 
	Map m = new Map(content, this);
	
	setVisible (true);
	JOptionPane.showMessageDialog(this, "Now, you can locate yourself on this giant, but\n " +
			"inacurate world map you got from an English missionary your met when you were 5.\n" +
			"Astonishingly, this map not only shows the continents on earth and the best locations\n " +
			"to establish ports or might exists a port, but also your current location and, the \n" +
			"pests of the seas, the pirates. Your goal is to visit each of these black dots spreaded\n" +
			"over the coasts of the continents.");
	JOptionPane.showMessageDialog(this, "Pirates(represented by the larger squares on the map: the hayinas of\n" +
			"the seas, is tenacious. If they spot you they will chase after you until smashing your ship. \n" +
			"However, be not afraid! The pirates has limited vision, and limited range of chasing. If it goes \n" +
			"too far from its original location it will just give up and go back to starve. You can check the \n" +
			"range by checking the Show Ranges on the bottom of the screen. The biggest circle is the farest \n" +
			"the pirate is willing to go, the middle one is its vision, and the smallest one its wandering circle.\n" +
			"Pirates are bored.");
	JOptionPane.showMessageDialog(this, "Moreover, if the programmer of this game can finish this game by 23 tonight\n" +
			"you may get to buy military submarines that can launch missiles from North Korea!");
	JOptionPane.showMessageDialog(this, "All is well. Now, to direct your ship, you can either use the traditional\n" +
			"and uncreative control panel on the bottom, which has buttons to speed up your ship in a certain direction.\n" +
			"Check it out! More convenient way is, after you have selected your ship (it will become orange, defalt is \n" +
			"selected), double click anywhere on the map where you want your ship to go. Isn't it easy? Oh, I forgot,\n" +
			"you haven't got to do anything. My bad. Ok, last thing, the more ports you visit, the more score you have.\n" +
			"Be careful, sometimes the map is not very accurate and you may hit the shore when it shows you doesn't!");
	JOptionPane.showMessageDialog(this, "Ready? Go!");
	

    }
    
}
