import java.awt.Graphics;


public class Island extends MapObject{
		
		public Island(int x, int y, String toName){
			rc[0] =x;
			rc[1] = y;
			this.name = toName;
		}
		
		public void draw (Graphics g){
			int x=dc[0]-15;
			int y=dc[1]-13;
			g.drawRect(x, y, 10, 10);
			//g.drawString(name, dc[0]+1, dc[1]-3);
			//String location = String.valueOf(rc[0]) + "," + String.valueOf(rc[1]);
			g.drawString(name, x, y+15);
		}
}
