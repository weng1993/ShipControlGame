import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.*;
public class Map  implements ActionListener{

	private Ship currentShip;
	private int currentType = 0;
	private ArrayList<Ship> ships;
	private static ArrayList<MapObject> objects;
	private ArrayList<Port> ports;
	private static ArrayList<Continent> continents;
	private ArrayList<Pirate> pirates;
	private Timer timer;
	private Container content;
	private MyCanvas canvas;
	private ControlPanel controlPanel;
	private JCheckBox c,addShip,display;
	private boolean displaySub=true;
	private boolean isPause = false;
	private boolean addingShip = false;
	static boolean displayPopulation = false;
	static State state;
	private Player newPlayer;
	Main parent;
	public Map (Container cont, Main main){
		parent = main;
		JOptionPane.showMessageDialog(main, "Hello, my friend! \n" +
				"You are about to march on a great journey" +
				" discovering the world. ");
		JOptionPane.showMessageDialog(main, "Now, let me introduce you to the rules. ");
		JOptionPane.showMessageDialog(main, 
				"First, your name is Vincent. \n" +
				"" +
				"You were born in a small fishing village in the East coast china.\n" +
				"Since young, your biggest dream remains sailing the world.\n" +
				"At the age of 50, the Emperor asked to see you, and ordained you to\n" +
				"to build the biggest ship on earth and discover other parts of the world.\n" +
				"After 5 years, you finished building the ship by yourself, of which you \n" +
				"named it after your favorite movie of all time, Titanic. \n" +
				"The next day, you start sailing. ");
		
		content = cont;
		//set Layout
		content.setLayout(new BorderLayout());
		addObjects();
		currentShip = newPlayer.getShip();
		currentShip.setPicked();
		//set canvas
		canvas = new MyCanvas(objects,this);
		content.add(canvas, BorderLayout.CENTER);
		//set panel
		JPanel south = new JPanel();
		controlPanel = new ControlPanel(this);
		//SET CHECKBOX
		JPanel T = new JPanel(new FlowLayout());
		setCheckBox(T);
		//SET SCORE PANEL(WEST)
		state = new State();
		content.add(state, BorderLayout.WEST);
		//SET CONTROLPANEL
		south.add(T,BorderLayout.EAST);
		south.add(new ControlPanel(this), BorderLayout.SOUTH);
		content.add(south, BorderLayout.SOUTH);
		//SET TIMER and START
		timer = new Timer(TimeControl.INIT_DELAY, this);
		TimeControl TC = new TimeControl(timer);
		content.add(TC, BorderLayout.NORTH);
		timer.start();
	}

	
	public void addObjects(){
		newPlayer = new Player(new Titanic(163,189,0,0));
			
		ports = new ArrayList<Port>();
		pirates = new ArrayList<Pirate>();
		objects = new ArrayList<MapObject>();
		ships = new ArrayList<Ship>();
		
		ships.add(newPlayer.getShip());
		pirates.add(new Pirate(882, 272, newPlayer.getShip()));
		pirates.add(new Pirate(955, 433, newPlayer.getShip()));
		pirates.add(new Pirate(751, 488, newPlayer.getShip()));
		pirates.add(new Pirate(414, 73, newPlayer.getShip()));
		for(Pirate p: pirates){
			ships.add(p);
		}
		for(Ship s: ships){
			objects.add(s);
		}
		//adding continents and islands
		continents = new ArrayList<Continent>();
		int[] SAx = {500,560,640,688,773,730,570,567,537};
		int[] SAy = {232,149,156,197,230,320,516,332,314};
		Continent SouthAmerica = new Continent(SAx,SAy);
		continents.add(SouthAmerica);
		objects.add(SouthAmerica);
		
		int[] EAx = {1011,1049,1106,1139,1126,1152,1186,1206,1221,1071,1010,985,985,927,939};
		int[] EAy = {117,98,87,121,75,72,125,97,17,23,34,60,99,116,157};
		Continent Europe = new Continent(EAx,EAy);
		continents.add(Europe);
		objects.add(Europe);
		
		int[] Jx = {273,260,210,250};
		int[] Jy = {48, 99,117,84};
		Continent Japan = new Continent(Jx,Jy);
		continents.add(Japan);
		objects.add(Japan);
		//ASIA
		int[] AAx = {248,199,153,178,149,118,103,142,162,125,66,98,64,14,0,0};
		int[] AAy = {0,38,59,100,144,72,92,138,192,245,253,307,335,303,221,0};
		Continent Asia = new Continent(AAx,AAy);
		continents.add(Asia);
		objects.add(Asia);
		int[] AUx = {30,32,99,131,156,191,171,119,61};
		int[] AUy = {401,392,360,381,364,396,451,439,449};
		Continent Australia = new Continent(AUx,AUy);
		continents.add(Australia);
		objects.add(Australia);
		//Africa
		int[] Ax = {1015,962,957,998,1056,1086,1116,1179,1205,1193,1223,1179,1181};
		int[] Ay = {163,213,295,342,339,419,497,484,400,311,244,219,159};
		Continent Africa = new Continent(Ax,Ay);
		continents.add(Africa);
		objects.add(Africa);
		
		Island Taiwan = new Island(171,244, "Taiwan");
		objects.add(Taiwan);
		Island NewZealand = new Island(230,460, "New Zealand");
		objects.add(NewZealand);
		Island IceLand = new Island(809,34, "IceLand");
		objects.add(IceLand);
		
		//adding ports
		//South America
		ports.add(new Port(537,180, "Bogota, Columbia", SouthAmerica));
		ports.add(new Port(500,232, "Guayaquil, Ecuador", SouthAmerica));
		ports.add(new Port(523,282, "Lima, Peru", SouthAmerica));
		ports.add(new Port(569,413, "SouthAmericantiago, Chile", SouthAmerica));
		ports.add(new Port(772,223, "Recife, Brazil", SouthAmerica));
		ports.add(new Port(719,331, "Rio de Janeiro, Brazil", SouthAmerica));
		ports.add(new Port(672,392, "Bueno Aires, Argentina", SouthAmerica));
		//Asia and Japan and Australia
		ports.add(new Port(163,186, "Hangzhou, China", Asia));
		ports.add(new Port(170,110, "Seoul, Korea", Asia));
		ports.add(new Port(97,306, "Vietnam, Vietnam", Asia));
		ports.add(new Port(246,103, "Osaka, Japan", Japan));
		ports.add(new Port(174,438, "Sydney, Australia", Australia));
		//Europe
		ports.add(new Port(951,148, "Sevilla, Spain", Europe));
		ports.add(new Port(1106,90, "Genova, Italy", Europe));
		ports.add(new Port(1047,98, "Marsille, France", Europe));
		ports.add(new Port(1187,125, "Athens, Greece", Europe));
		//Africa

		ports.add(new Port(1045,160, "Tunis, Tunis", Africa));
		ports.add(new Port(1153,160, "Alexandria, Egypt", Africa));
		ports.add(new Port(960,244, "Cabo Verde", Africa));
		ports.add(new Port(1055,338, "Nigeria", Africa));
		ports.add(new Port(1117,494, "Cape Town, South America", Africa));
		
		for(Port s: ports){
			objects.add(s);
		}
	}
	private void setCheckBox(JPanel T){
		c  = new JCheckBox();
		T.add(new JLabel("display submarines"));
		c.addActionListener(this);
		c.setSelected(true);
		T.add(c);
		T.add(new JLabel("Show ranges"));
		addShip = new JCheckBox();
		addShip.addActionListener(this);
		addShip.setSelected(false);
		T.add(addShip);
		T.add(new JLabel("show poplation"));
		display = new JCheckBox();
		display.addActionListener(this);
		display.setSelected(false);
		T.add(display);
	}
	
	public void actionPerformed(ActionEvent e){
		if(newPlayer.getShip().isSinked()){
			JOptionPane.showMessageDialog(parent, 
					"Ship sinked. Game over!");
			System.exit(1);
		}
		
		if(e.getSource() == c){
			displaySub = !displaySub;
			for(Ship s: ships){
				if(s.getString().equals("Submarine")){
					((Submarine)s).setDisplay();
				}
			}	
		}
		if(e.getSource() == addShip){
			for(Pirate p: pirates){
				p.showRange();
			}
		}
		if(e.getSource() == display){
			displayPopulation=!displayPopulation;
		}
		
		//Many things happens here:
		//	#1 ships collide with ships
		//  #2 ships hit  continent(s)
		//	#3 ships visit ports
		for(Ship c: ships){
			if(!c.isSinked()){
				c.Tmove(timer.getDelay());
				for(Ship s: ships){
					if(!c.getString().equals(s.getString())){
						if(c.collide(s)){
						System.out.println(c.getString()+ " and " + s.getString()+ 
							    " COLLIDE!! at "+c.getX()+","+c.getY()+" and "+s.getX()+","+s.getY());
						c.stop();
						c.setSink();
						}
					}
				}
				//For port stuff
				for(Port p: ports){
					if(c.collide(p)) {
						if(!c.isLeaving())
						  c.stop();
						if(p.establishPort())
							state.updateScore();
						if(!c.isLanded()&& !c.isLeaving()){
							c.setLanded();
						}
					}
				}
				if(c.isLeaving()){;
					int counter = 0;
					for(Port p: ports){
						if(c.collide(p)) 
							counter++;
					}
					if (counter == 0)
						c.setLeaving();
				}
				
				for(Continent continent: continents){
					if(c.collide(continent)){
						System.out.println(c.getString()+ " and " + continent.getString()+ 
							    " COLLIDE!! at "+c.getX()+","+c.getY());
						c.stop();
						c.setSink();
					}
				}
			}
		}
		canvas.repaint();
	}
	public void setCurrentType(int i){
		currentType = i;
	}
	public Ship getCurrentship(){
		return currentShip;
	}
	public void setCurrentship(Ship toSet){
		currentShip = toSet;
	}
	public void deleteCurrentship(){
		ships.remove(currentShip);
		if(!ships.isEmpty())
			currentShip = ships.get(0);
		
	}
	public ArrayList<Port> getPorts(){
		return ports;
	}
	public ArrayList<Ship> getShips(){
		return ships;
	}
	public static ArrayList<MapObject> getMapObjects(){
		return objects;
	}
	public static ArrayList<Continent> getContinents(){
		return continents;
	}
	
	public void pause(){
		if(isPause) timer.start();
		else timer.stop(); 
		isPause = !isPause;
	}
}