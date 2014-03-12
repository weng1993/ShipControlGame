import java.awt.Graphics;


public abstract  class Ship extends MapObject{
	protected int w, h;
	protected boolean sinked = false;
	protected boolean current = false;
	protected final static int MAX_SX = 15;
	protected final static int MAX_SY = 15;
	protected boolean landed = false;
	protected boolean leaving = false;//true when ship tries to leave port
	protected int MAXSPEED;
	public Ship(){
	}
	public Ship (int x, int y, int dx, int dy){
		int sx = MAX_SX * dx / MyCanvas.DRAGLIMIT_X; 
		int sy = MAX_SY * dy / MyCanvas.DRAGLIMIT_Y; 
		zoomset(x,y,sx,sy);

	}
	public boolean isLanded(){
		return landed;
	}
	public void setLanded(){
		landed = !landed;
	}
	//custom function 
	public void draw(Graphics g){}

	public String getString(){
		return name;
	}
    public void speedup (int dx, int dy){
    	if(!sinked){
    	  if (rc[2]<=MAX_SX) rc[2] += dx;
    	  if (rc[3]<=MAX_SY) rc[3] += dy;
    	}
    }
    //used abs() in MapObject
    //Check if this ship collide with b. If so, set this to sink, but not b.
    //INVARIANT: to get into the checking procedure, this ship must not be sinked.
	public boolean collide(MapObject b){
		if (!sinked){
			if ((abs(getX()-b.getX())<=5) && (abs(getY()-b.getY())<=5) )
				return true;
			}

			//Only continent is missed checking. Now check continent:
			if(b.getString() == "continent"){
				//System.out.println(b.getString());
				if (((Continent)b).contains(this))	{
					return true;
				}
			}
			
		return false;
	}
	public boolean mousePicked(int x, int y){
		//System.out.println(abs(dc[0]-x) +" "+ abs(dc[1]-y));
		if ((abs(dc[0]-x)<=50) && (abs(dc[1]-y)<=50)) {
			System.out.println("PICKED!!!!");
			return true;
		}
			
		else
			return false;
	}
    public int getX(){return rc[0];}
    public int getY(){return rc[1];}
    public int getZoomX(){return rc[0];}
    public int getZoomY(){return rc[1];}
    public int getW(){return w;}
    public int getH(){return h;}
    public  void convertCoordd(){
		dc[0] = (int)(rc[0] * zoom) ;
		dc[1] = (int)(rc[1] * zoom) ;
    }

    public void stop (){
    	rc[2] = 0;
    	rc[3] = 0;
    }
    public void Tmove(int delay){
    	rc[0] += ( rc[2] * delay * 0.001);
    	rc[1] += ( rc[3] * delay * 0.001);
    	
    	if (rc[0] > MyCanvas.WIDTH+30) rc[0] = -30;
    	if (rc[0] < -30) rc[0] = MyCanvas.WIDTH+30;
    	if (rc[1] > MyCanvas.HEIGHT+20) rc[0] = -20;
    	if (rc[1] < -20) rc[0] = MyCanvas.HEIGHT+20;
    }
    //SETTING coordinates and stuff

    protected void set(int x, int y, int sx, int sy){
    	rc[0] = x;
    	rc[1] = y;
    	rc[2] = sx;
    	rc[3] = sy;
    	
	}
	public void goingTo(int x, int y){
		
		int xDiff = rc[0] - x;
		int yDiff = rc[1] - y;
		int distance = distance( x,  y);
		if(abs(xDiff) > 3){
			rc[2] = -(int)((double)MAXSPEED* xDiff/ distance);
		}
		else
			rc[2] = 0;
		if(abs(yDiff)>3) 
			rc[3] = -(int)((double)MAXSPEED * yDiff/ distance);
		else
			rc[2] = 0;
	}
    protected void zoomset(int x, int y, int sx, int sy){
    	dc[0] = x;
    	dc[1] = y;
    	reverseCoord(0,0);
    	rc[2] = sx;
    	rc[3] = sy;
    	
	}
	public int distance (int x, int y){
		int xDiff = rc[0] - x;
		int yDiff = rc[1] - y;
		double distance = Math.sqrt((double)(xDiff * xDiff + yDiff * yDiff));
		return (int) distance;
	}
    public void display(){
    	System.out.println(dc[0]+ ","+dc[1]);
    }
    public void setPicked(){
    	current = true;
    }
    public void setUnPicked(){
    	current = false;
    }
    public void setSink(){
    	sinked = true;
    }
    public boolean isSinked(){
    	return sinked;
    }
    public boolean isLeaving(){
    	return leaving;
    }
    public void setLeaving(){
    	leaving = !leaving;
    }
}