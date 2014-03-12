import java.awt.*;

import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.net.URL;

//MyCanvas is responsible for receiving wheel signals for zooming 
//and mouse click signals for adding ships.


public class MyCanvas extends JComponent implements 
											MouseListener,
											MouseWheelListener{
	private int x_pressed, y_pressed, x_clicked, y_clicked;
	
	final static int WIDTH = 1240;
	final static int HEIGHT = 600;
	static int zoomFocusX = 0;
	static int zoomFocusY = 0;
	static final int DRAGLIMIT_X = 50;
	static final int DRAGLIMIT_Y = 50;
	private ArrayList<MapObject> objects;
	private Map m;
	private double notches =1.0;
	private final double INC = 0.1;
	private boolean pressed = false;
	MyCanvas(ArrayList<MapObject> objectlist, Map map){
		m = map;
		objects = objectlist;
		Dimension d = new Dimension (WIDTH,HEIGHT);
		setPreferredSize(d);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		
		
	}
	public void paintComponent (Graphics g){ 
		paintBorder(g);
		for(MapObject c: objects){
			c.convertCoord(zoomFocusX,zoomFocusY);
		    c.draw(g);
		}
		//DISPLAY PICKED LOCATION on CANVAS
		if(pressed)
			drawCoord(g);
		
		if(Map.displayPopulation){
			System.out.print("population");
			for(Port p: m.getPorts()){
				p.displayPopulation(g);
			}
		}
		
			
		
    }
	 public void mouseWheelMoved(MouseWheelEvent e){
		 int notinc = -e.getWheelRotation();
		 notches += (double)notinc * INC;
		 MapObject.setZoom((double)notches);

		 zoomFocusX = e.getX();
		 zoomFocusY = e.getY();
		 
	 }
	 public void drawCoord(Graphics g ){
		 String coord = Integer.toString(x_pressed) +", "+
				 		Integer.toString(y_pressed);
		 g.drawString(coord, x_pressed, y_pressed);
		 

	 }
	 public void mousePressed(MouseEvent e) {
		 pressed = true;
		 x_pressed = e.getX();
		 y_pressed = e.getY();
		 System.out.println(x_pressed+","+ y_pressed);
		 
		 
     }
     public void mouseReleased(MouseEvent e) {
    	 pressed = false;
    	 int x_dragged = limit_drag(e.getX()-x_pressed);
    	 int y_dragged = limit_drag(e.getY()-y_pressed);
     }
     private int limit_drag(int in){
    	 if (in > DRAGLIMIT_X) return DRAGLIMIT_X;
    	 if (in < -DRAGLIMIT_X) return -DRAGLIMIT_X;
    	 else return in;
     }
     //ADDING SHIP with MOUSEDRAG
 		/*
    	 int x_dragged = limit_drag(e.getX()-x_pressed);
    	 int y_dragged = limit_drag(e.getY()-y_pressed);
    	 m.addShip((int)(x_pressed), (int)(y_pressed), x_dragged, y_dragged);
     }
     private int limit_drag(int in){
    	 if (in > DRAGLIMIT_X) return DRAGLIMIT_X;
    	 if (in < -DRAGLIMIT_X) return -DRAGLIMIT_X;
    	 else return in;
     }
     */ 
     public void mouseEntered(MouseEvent e) {
     }
     public void mouseExited(MouseEvent e) {
     }
     public void mouseClicked(MouseEvent e) {
    	 //MOUSEPICKING SHIP
    	 
    	 if (e.getClickCount() == 1){
    	 	for(Ship s: m.getShips()){
    			if (s.mousePicked(x_pressed, y_pressed)){//island can't be picked
    				if(m.getCurrentship()!=null)
    					m.getCurrentship().setUnPicked();//dishighlight previously
    														//pickedship
    				s.setPicked();//highlight this ship
    				m.setCurrentship(s);//make it current
    				return;
    			}
    	 	}  	 	
    	 }
    	 
    	 //MOVING CORRENTSHIP
    	 if (e.getClickCount() == 2){
    		 if (m.getCurrentship().isLanded())
    			 m.getCurrentship().setLeaving();
    		 
    		 m.getCurrentship().goingTo(e.getX(), e.getY());
    		 
    	 }
     }
 	public void setColor(Color newColor) {
		 setOpaque(true);
	  	 setBackground(newColor);
	}
   
}