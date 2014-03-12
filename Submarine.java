import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Submarine extends Ship{
	final static int MAX_SX = 15;
	final static int MAX_SY = 15;
	private boolean unDisplay;

	public Submarine(int x, int y,  int sx, int sy){
		super(x,y,sx,sy);
		MAXSPEED = 10;
		this.w = 25;
		this.h = 8;
		name = "Submarine";
		
		
	}
	public void draw (Graphics g) { //automatically called by repaint() in Canvas
		//g.drawRect(x, y, w, h);
		
		int x = dc[0]-15;
		int y = dc[1]-5;
		
		Graphics2D g2 = (Graphics2D)g;

		if(!unDisplay){
		if(current) g2.setColor(Color.ORANGE);
		if(sinked) g.setColor(Color.GRAY);
		int[]xpoints = {x,x+w/3,x+w/3,x+2*w/3,x+2*w/3,x+w,x+w,x};
		int[]ypoints = {y, y, y-h/2, y-h/2, y, y, y+ h/2, y + h/2};
		
		g2.drawRoundRect(x, y, w, h, 20, 20);
		g2.drawString("("+name+")", x, y+41);
		
		String location = String.valueOf(rc[0]) + "," + String.valueOf(rc[1]);
		g.drawString(location, x+7, y+30);
		g2.setColor(Color.BLACK);}
	
	}
	public void setDisplay(){
		unDisplay = !unDisplay;
	}

	public void reset(){
		set(30,100,0,0);
	}
    
}
