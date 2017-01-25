import java.lang.Math;
/**
	This class takes an enemy, tracks his position, movement, and player interaction.
*/
public class Enemy{
	private int Attack, Health, Armor, X_Pos, Y_Pos, ID;
	
	/**
		Creates the enemy and assigns a random position.
		@param Attack, Health, Armor
	*/
	public Enemy(int at, int he, int ar, int id, RoomGenerator[][] maze, int maze_size){
		Attack = at;
		Health = he;
		Armor = ar;
		X_Pos = (int)(Math.random()*maze_size);
		Y_Pos = (int)(Math.random()*maze_size);
		ID = id;
		maze[X_Pos][Y_Pos].changeOccupy(ID);
	}
	
	/**
		Moves the enemy throughout maze, while making sure there is a door in that direction.
		@param maze
	*/
	public void Enemy_Move(RoomGenerator[][] maze){
		if(ID!=-1){
			boolean decision = false;
			while(!decision){
				int direction = (int)(Math.random()*4);
				if(direction == 0  &&  maze[X_Pos][Y_Pos].doorNorth()){
					Y_Pos--;
					decision = true;
					maze[X_Pos][Y_Pos].changeOccupy(ID);
				}
				else if(direction == 1  &&  maze[X_Pos][Y_Pos].doorSouth()){
					Y_Pos++;
					decision = true;
					maze[X_Pos][Y_Pos].changeOccupy(ID);
				}
				else if(direction == 2  &&  maze[X_Pos][Y_Pos].doorEast()){
					X_Pos++;
					decision = true;
					maze[X_Pos][Y_Pos].changeOccupy(ID);
				}
				else if(direction == 3  &&  maze[X_Pos][Y_Pos].doorWest()){
					X_Pos--;
					decision = true;
					maze[X_Pos][Y_Pos].changeOccupy(ID);
				}
			}
		}
	}
	
	
	/**
		This method generates how much damage the enemy received from an attack.
		@param pAttack
		@return received
	*/
	public int damageRecieved(int pAttack, RoomGenerator[][] maze){
		int x = (int)(Math.random()*20)+pAttack-20;
		int y = (int)(Math.random()*20)+Armor-20;
		int recieved = (x - y > 0) ? x-y : 0;
		Health-=(recieved);
		if(Health <= 0){
			ID = -1;
			maze[X_Pos][Y_Pos].changeOccupy(ID);
		}
		return recieved;
	}
	
	/**
		Returns enemy's health
		@return Health
	*/
	public int getHealth(){
		return Health;
	}
	
	/**
		Returns enemy's attack
		@return Attack
	*/
	public int getAttack(){
		return Attack;
	}
	
	/**
		Returns enemy's armor
		@return Armor
	*/
	public int getArmor(){
		return Armor;
	}
	
	/**
		Returns enemy's X position
		@return X_Pos
	*/
	public int getX(){
		return X_Pos;
	}
	
	/**
		Returns enemy's Y position
		@return Y_Pos
	*/
	public int getY(){
		return Y_Pos;
	}
	
	/**
		Returns enemy's index
		@return ID
	*/
	public int getID(){
		return ID;
	}
}


