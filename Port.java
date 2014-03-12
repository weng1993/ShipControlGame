import java.awt. *;
import javax.swing. *;
public class Port extends MapObject{
	private Continent parentContinent;
	private int population = 0;
	private String name;
	private boolean discovered = false;
	
	public Port(int x, int y, String tname, Continent parent){
		this.rc[0] = x;
		this.rc[1] = y;
		this.name = tname;
		parentContinent = parent;
	}
	public void draw(Graphics g){
		g.fillRect(getX(),getY(), 5, 5);
		if(discovered)
		g.drawString(name, getX(), getY());
	}
	public void displayPopulation(Graphics g){
		String pop = Integer.toString(population);
		g.drawString(pop, getX(), getY() + 15);
	}
	public boolean establishPort(){
		if(!discovered){
			discovered = true;
			System.out.println(name+ " is established!!");
			return true;
		}
		return false;
	}
}
