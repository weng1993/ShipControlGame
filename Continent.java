import java.awt.*;
import javax.swing.*;
public class Continent extends MapObject {
	private int[] xports;
	private int[] yports;
	private Port[] ports;
	private Polygon myPolygon;
	public Continent(int[] xdots, int[] ydots ){
		name = "continent";
		xports = xdots;
		yports = ydots;
		myPolygon = new Polygon(xports, yports, xports.length);
	}
	public void draw (Graphics g){	
		g.drawPolygon(myPolygon);
	}
	//packing Polygon's contains() methods
	public boolean contains(Ship s){
		return myPolygon.contains(s.getX()-10, s.getY()-10,s.getW()/2,s.getH()/2);
		
	}
	public boolean containsPoint(int x, int y){
		return myPolygon.contains(x, y);
	}
	
}
