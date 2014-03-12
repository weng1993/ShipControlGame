import java.awt.Graphics;


public class MapObject {
	protected int[] rc = new int[4];;//real coordinate and speed
	protected int[] dc = new int[2];//display coordinate and speed
	protected String name;
	protected static double zoom=1;
	protected boolean current = false;
	protected boolean displayCoord= false;
	public MapObject(){
	}
	//custom function 
	public void draw(Graphics g){}
	public static void setZoom(double set){
		if(0<set&&set<2)
		zoom = set;
	}
	public static double getZoom(){
		return zoom;
	}
	public String getString(){
		return this.name;
	}
    //NEEDS WORK
    public  void convertCoord(int x, int y){
    	dc[0] = rc[0];
		dc[1] = rc[1];
		dc[0] = (int)(x + (rc[0]-x)* zoom) ;
		dc[1] = (int)(y + (rc[1]-y)* zoom) ;
	}
    public  void reverseCoord(int x, int y){
    	//dc[0] = rc[0];
		//dc[1] = rc[1];
		rc[0] = (int)(dc[0]/zoom + x) ;
		rc[1] = (int)(dc[1]/zoom + y) ;
	}
	public static boolean collide(Ship a, Ship b){
		if ((abs(a.getX()-b.getX())<=30) && (abs(a.getY()-b.getY())<=25) )
			{System.out.println(a.getString()+ " and " + b.getString()+ 
			" COLLIDE!! at "+a.getX()+","+a.getY()+" and "+b.getX()+","+b.getY());
			a.stop();b.stop();
			a.setSink();
			b.setSink();
			return true;}
		return false;
	}

	public int getX(){return rc[0];}
    public int getY(){return rc[1];}
    public  void convertCoordd(){
		dc[0] = (int)(rc[0] * zoom) ;
		dc[1] = (int)(rc[1] * zoom) ;
    }
    //SETTING coordinates and stuff
    public static int power(double a, int b)
    {
            double power = 1;
            for(int c=0;c<b;c++)
            power*=a;
            return (int)power;
    }
    public void setPicked(){
    	current = true;
    }
    public void setUnPicked(){
    	current = false;
    }
    public int trim ( int max, int input){
    	if(input > max){
    		return max;
    	}
    	return input;
    }

	public static int abs(int in){
		if (in < 0) return -in;
		else return in;
	}
    protected void set(int x, int y, int sx, int sy){
    	rc[0] = x;
    	rc[1] = y;
    	rc[2] = sx;
    	rc[3] = sy;
    	
	}
    protected void zoomset(int x, int y, int sx, int sy){
    	dc[0] = x;
    	dc[1] = y;
    	reverseCoord(0,0);
    	rc[2] = sx;
    	rc[3] = sy;
    	
	}
    
}
