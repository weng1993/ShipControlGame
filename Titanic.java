import java.awt.Color;
import java.awt.Graphics;


public class Titanic extends Ship{

	private static int counter = 0;
	
	public Titanic (int x, int y, int sx, int sy){
		
		super(x,y,sx,sy);
		MAXSPEED = 15;
		this.w = 25;
		this.h = 15;
		Titanic.counter++;
		this.name = "Titanic"+counter;
	}
	public void draw (Graphics g) { //automatically called by repaint() in Canvas
		//g.drawRect(x, y, w, h);
		if(current) g.setColor(Color.ORANGE);
		if(sinked) g.setColor(Color.GRAY);
		int x = dc[0]-w/2;
		int y = dc[1];
		int[]xpoints = {x,x+w/3,x+w/3,x+2*w/3,x+2*w/3,x+w,x+w,x};
		int[]ypoints = {y, y, y-h/2, y-h/2, y, y, y+ h/2, y + h/2};
		g.fillPolygon(xpoints,ypoints, 8);
		//g.drawString(name, x+7, y+30);
		String location = String.valueOf(rc[0]) + "," + String.valueOf(rc[1])
				+ "," + String.valueOf(rc[2])+ "," + String.valueOf(rc[3]);
		g.drawString(location, x+7, y+30);
		g.setColor(Color.BLACK);
	}

	public void reset(){
		set(30, 50, 4,10);
	}
    
}
