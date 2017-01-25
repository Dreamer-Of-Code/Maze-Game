import java.lang.Math;
/**
	This class creates a room with doors. Made for the use of a maze.
*/
public class RoomGenerator{
	private boolean north_door, east_door, south_door, west_door, occupied;
	private int ID;
	
	
	
	/**
		This method creates the room to prevent nullPointerExceptions, but keep the room empty
	*/
	public RoomGenerator(){
		north_door = false;
		south_door = false;
		east_door = false;
		west_door = false;
		occupied = false;
		ID = -1;
	}
	
	
	
	/**
		This method creates the room and the doors in the room.
		@param max_doors, min_doors, south_door, east_door
	*/
	public RoomGenerator(int max_doors, int min_doors, boolean s, boolean e, RoomGenerator[][] maze, int x, int y, int maze_size){
		int door_count=0;
		while(door_count<min_doors){
			int num = (int)(Math.random()*4);
			if(num==0 && y > 0)
				if(!north_door){
					north_door=true;
					door_count++;
					maze[x][y-1].change_doorSouth();
				}
			if(num==1 && y < maze_size-1)
				if(!south_door){
					south_door=true;
					door_count++;
					maze[x][y+1].change_doorNorth();
				}
			if(num==2 && x < maze_size-1)
				if(!east_door){
					east_door=true;
					door_count++;
					maze[x+1][y].change_doorWest();
				}
			if(num==3 && x > 0)
				if(!west_door){
					west_door=true;
					door_count++;
					maze[x-1][y].change_doorEast();
				}
		}
		int a = (int)(Math.random()*2);
		while(door_count < max_doors && a != (int)(Math.random()*2)){
			int num = (int)(Math.random()*4);
			if(num==0)
				if(!north_door){
					north_door=true;
					door_count++;
				}
			if(num==1)
				if(!south_door){
					south_door=true;
					door_count++;	
				}
			if(num==2)
				if(!east_door){
					east_door=true;
					door_count++;	
				}
			if(num==3)
				if(!west_door){
					west_door=true;
					door_count++;
				}
		}
	}
	
	
	
	/**
		This method sets north_door to true
	*/
	public void change_doorNorth(){
		north_door = true;
	}
	
	/**
		This method sets south_door to true
	*/
	public void change_doorSouth(){
		south_door = true;
	}
	
	/**
		This method sets east_door to true
	*/
	public void change_doorEast(){
		east_door = true;
	}
	
	/**
		This method sets west_door to true
	*/
	public void change_doorWest(){
		west_door = true;
	}
	
	
	
	/**
		This method sets north_door to false
	*/
	public void false_doorNorth(){
		north_door = false;
	}
	
	/**
		This method sets south_door to false
	*/
	public void false_doorSouth(){
		south_door = false;
	}
	
	/**
		This method sets east_door to false
	*/
	public void false_doorEast(){
		east_door = false;
	}
	
	/**
		This method sets west_door to false
	*/
	public void false_doorWest(){
		west_door = false;
	}
	
	
	
	/**
		This method returns if there is a door to the North
		@return north_door
	*/
	public boolean doorNorth(){
		return north_door;
	}
	/**
		This method returns if there is a door to the South
		@return south_door
	*/
	public boolean doorSouth(){
		return south_door;
	}
	/**
		This method returns if there is a door to the East
		@return east_door
	*/
	public boolean doorEast(){
		return east_door;
	}
	/**
		This method returns if there is a door to the West
		@return west_door
	*/
	public boolean doorWest(){
		return west_door;
	}
	
	
	
	/**
		This method returns if the room is occupied
		@return occupied
	*/
	public boolean isOccupied(){
		return occupied;
	}
	
	/**
		This method returns the enemies index
		@return ID
	*/
	public int OccupyID(){
		return ID;
	}
	
	/**
		This method changes if an enemy is occupying it
		@param id
	*/
	public void changeOccupy(int id){
		if(occupied){
			occupied = false;
			ID = -1;
		}
		else{
			occupied = true;
			ID = id;
		}
	}
	
}




