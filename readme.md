Ship Control Game - World Exploration(with pirates)

	Game rules:
	The player controls one ship and visit all ports to get victory. If the ship is hit by a pirate or hits the shore, game is over.
	
	UI design:
	There are buttons to control the ship.
	User can also direct the ship by double click on the canvas.
	Checkingbox is used to change overall state including the range of pirates. 

  Meanings of Ranges: 
    Red:Pirates will chase you if you enter the red circle
    Green: You are safe if you escaped out of green circle
    Blue: Direction of Pirate
  Symbols:
    Squares: Pirates or Islands, essentially are single points.
    Poligons: Continents
    Dots: Ports, cities.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    An outline showing the inheritance hierarchy
    	
    	Ship
    		Kayak
    		Titanic
    		Submarine
    		Island
    	
    An outline showing the aggregation hierarchy (which objects contain or "own" which other objects)
    	Map 
    		Ship ships
    		
    		ControlPanel
    			MyButton buttons
    			ScrollBar zoombar
    		Timer
    		MyCanvas
    				
    				
      Details see the UML diagram
    A list showing "uses" or collaboration relationships (which objects use which other objects to perform functions)
    	Timer -> Map -> MyCanvas repaint() -> MyCanvas paintComponent -> ships.draw/ ships.convertCoordinate //time tick actionPerformed
    	
    	
    	ScrollBar zoombar -> Ship.zoom //change the zoom index
    	MyButton buttons -> Ship ships // change speed of each ship, display ship's location, delete currentShip.
    	
    	
    	
    	
    The information hiding "secrets" of each of your classes (i.e., what design decisions are entirely encapsulated within that class). 
    	All instance varibals are either constants or private or protected. Specific instance variables for callback are accessed with 
    	specific fuctions like setCurrentShip, setCurrentType.
    	
    	
    	
    	
   5.5 changes:
   1) Continent System: #1 changing ship.collide fucntion for continent.
   						#2 INVARIANT: Ship does not collide with ports. 
   2) Adding Ship mode  //Implemented
   3) Mouse Click on screen to know location //Implemented
   4) Display population mode to display population of all ports
   5) Game mode: 1. Exploring Continent (port name shown after discovered) //implemented
   			     2. Transporting passengers, avoiding islands and pirates
   			     3. Pirate mode on/off ; Pirate regeneration
   			     4. Making money
   				 
   6) Better organization of instance variables and actions according to world of ideas.
  
   
   Deleted feature:
   1) adding ship;
   
  