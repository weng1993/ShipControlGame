import java.lang.Math.*;
import java.util. *;

import java.net.URL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
//This class supplies functions for the pirates to think!

//RULES:
//1.If a pirate ship is hit by a bomb, it is deleted.
//2.A pirate ship walks around in a small area, once a commercial cargo
//	is spotted, it changes its speed gradually and chase staightly towards
//	the cargo, until it hits the cargo, or it gets too far from its origin
//3. Pirate will try to avoid crashing: avoiding another pirate ship as 1st
//	 priority if it is too close; same rule applies for a sea shore.
//4. That's as much as I can think of now.

public class Pirate extends Ship{
	private int xOrigin, yOrigin;
	private final int RANGE = 30;
	private final int REACH = 150;
	private final int FARREACH = 300;
	private boolean onMission = false;
	private final int MAXSPEED = 10;
	private Ship b; //Player
	private static int counter = 0;
	private Image shipImage;
	private boolean goingBack = false;
	private boolean show = false;
	public Pirate (int x, int y, Ship player){
		counter ++;
		name = "Pirate"+counter;
		b = player;
		xOrigin = x;
		yOrigin = y;
		rc[0] = x-50;
		rc[1] = y-50;
		rc[2] = 4;
		rc[3] = -1;
		w = 30;
		h = 30;
		name = "Pirate";
		//List<BufferedImage> image = ICODecoder.read(new File("input.ico"));
		/*
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("pirate3.png"));
		} catch (IOException e) {
			System.out.println("read image fail");
		}
		try{
		URL url = new URL("http://www.mkyong.com/image/mypic.jpg");
		shipImage = ImageIO.read(url);

		}
		catch(Exception e){
			System.out.println("load fail");
		}*/
		
		//shipImage =  new ImageIcon("pirate3.png").getImage();;
	}
	//If wanders too far, go back. Then decide what to do
	//override
	public void goingTo(int x, int y){
		
		int xDiff = rc[0] - x;
		int yDiff = rc[1] - y;
		int distance = distance(xOrigin, yOrigin);
		if(abs(xDiff) > 3){
			rc[2] = -(int)((double)MAXSPEED * xDiff/ distance );
		}
		else
			rc[2] = 0;
		if(abs(yDiff)>3) 
			rc[3] = -(int)((double)MAXSPEED * yDiff/ distance);
		else
			rc[2] = 0;
	}
    public void Tmove(int delay){
    	//normal moves along the speed direction
    	rc[0] += ( rc[2] * delay * 0.001);
    	rc[1] += ( rc[3] * delay * 0.001);
    	
    	//walking in the range, blindly
    	if(!onMission){
    		//if (abs(rc[0]-xOrigin )> RANGE) {rc[2]=-rc[2]; System.out.println(abs(rc[0]-xOrigin ));}
    		//if (abs(rc[1]-yOrigin )> RANGE) {rc[3]=-rc[3];System.out.println(rc[1]+" "+ yOrigin);}
    		if (distance(xOrigin, yOrigin)> RANGE) goingTo(xOrigin,yOrigin);
    	//if there is a cargo nearby, switch on mission
	    	if((!goingBack) && (distance(b.getX(), b.getY())<= REACH)){
	    		onMission = true;
	    		System.out.println("Pirate on mission.");
	    		//first stop than speed up towards the cargo
	    		stop();
	    	}
    	}
    	else if (!goingBack) {
    		inLineSpeedWithB();
    		if(closeToShore(Map.getContinents().get(0))){
    			goingTo(xOrigin, yOrigin);
    			onMission = false;
    			goingBack = true;
    		}
    		if(distance(xOrigin, yOrigin) > FARREACH){
    			onMission = false;
    			System.out.println("Mission failed, Going back");
    			goingBack = true;
    		}
    			
    	}
    	
    	//if there is a obstacle very close, instantly change speed to avoid hitting.
    	//if(obstacle(Map.getMapObjects()))
    		//goBack();
    		
    	
    }
    //checking the four points if it is in the continent
    private boolean closeToShore(Continent continent){

    	if((continent.containsPoint(rc[0], rc[1]+30)) ||
    	continent.containsPoint(rc[0]+30, rc[1]) ||
    	continent.containsPoint(rc[0], rc[1]-30) ||
    	continent.containsPoint(rc[0]-30, rc[1]))
    		return true;

    	return false;
    }
    private boolean obstacle(ArrayList<MapObject> objects){
    	for(MapObject m: objects){
    		if(m != this){
    		if(m.getString() != "continent"){
	    		if ((abs(getX()- m.getX())<=10) && (abs(getY()-m.getY())<=10) )
	    			return true;
    		}
    		else{
    			//if in the near future it will hit the continent, go back
    			if ( ((Continent)m).containsPoint(rc[0]+rc[2]*10, rc[1]+rc[3]*10))
    				return true;
    		}}
    			
    		
    	}
    	
		return false;
    }

    public void draw(Graphics g){
    	//System.out.print("drawing prirate");
    	//g.drawPolygon(xPoints, yPoints, nPoints)
    	//g.drawImage(shipImage, dc[0]-w/2, dc[1]-h/2, w, h, null);
    	g.drawRect(dc[0]-w/4, dc[1]-h/4, w/2, h/2);
    	
		String location = String.valueOf(rc[0]) + "," + String.valueOf(rc[1])+
				"," + String.valueOf(rc[2])+"," + String.valueOf(rc[3]);
		g.drawString(location, dc[0], dc[1]+30);
		
		if(show){
			g.setColor(Color.CYAN);
			g.drawLine(getX(), getY(), getX()+rc[2]*10, getY()+rc[3]*10);
			g.setColor(Color.gray);
			g.drawOval(xOrigin-RANGE, yOrigin-RANGE, 2*RANGE, 2*RANGE);
			g.setColor(Color.RED);
			g.drawOval(getX()-REACH, getY()-REACH, 2*REACH, 2*REACH);
			g.setColor(Color.green);
			g.drawOval(xOrigin-FARREACH, yOrigin-FARREACH, 2*FARREACH, 2*FARREACH);
			g.setColor(Color.black);
		}
    }

	//beta1: simple speed changing method: setting speed toward target
	private void  inLineSpeedWithB(){
		double xDiff = rc[0] - b.getX();
		double yDiff = rc[1] - b.getY();
		double denominator = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		rc[2] = -(int)((double)MAXSPEED * xDiff/ denominator);
		rc[3] = -(int)((double)MAXSPEED * yDiff/ denominator);
		
		
	}
	public void showRange(){
		show = !show;
	}
	
}
