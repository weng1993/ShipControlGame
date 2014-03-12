
public class Player {
	String playerName;
	String shipType;
	State currentState;
	Ship theShip;
	
	public Player (Ship ship){
		theShip = ship;
	}
	public void setShipType (String name){
		shipType = name;
	}
	public void setState(State state){
		currentState = state;
	}
	public void setShip(Ship ship){
		theShip = ship;
	}
	public Ship getShip(){
		return theShip;
	}
}
