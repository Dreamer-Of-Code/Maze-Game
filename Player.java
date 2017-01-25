import java.util.Scanner;
import java.lang.Math;

/**
	This class keeps track of a player, his attack, health, armor, and position.
*/

public class Player{
	private int Attack, Health, Armor, X_Pos, Y_Pos;
	
	/**
		Creates the player and assigns a position.
		@param Attack, Health, Armor, X_Pos, Y_Pos
	*/
	public Player(int at, int he, int ar, int xp, int yp){
		Attack = at;
		Health = he;
		Armor = ar;
		X_Pos = xp;
		Y_Pos = yp;
	}
	
	/**
		Prompts the user for input, and moves the player based on input.
		@param maze
	*/
	public void Player_Move(RoomGenerator[][] maze){
		Scanner in = new Scanner(System.in);
		boolean decision = false;
		boolean exit_catch = false;
		int direction = 0;
		String n = "North: 1\n";
		String s = "South: 2\n";
		String ea = "East: 3\n";
		String w = "West: 4\n";
		while(!decision){
			while(!exit_catch)
				try{
					System.out.println();
					System.out.println("Stats: Health: " + Health + " Attack: " + Attack + " Armor: " + Armor);
					System.out.println("Coordinates: X: " + X_Pos + " Y: " + Y_Pos);
					System.out.println("Which direction?");
					if(maze[X_Pos][Y_Pos].doorNorth())System.out.print(n);
					if(maze[X_Pos][Y_Pos].doorSouth())System.out.print(s);
					if(maze[X_Pos][Y_Pos].doorEast())System.out.print(ea);
					if(maze[X_Pos][Y_Pos].doorWest())System.out.print(w);
					System.out.println("Wait: 5");
					System.out.print("Enter Decision: ");
					direction = in.nextInt();
					in.nextLine();
					exit_catch = true;
				}
				catch(Exception e){
					in.nextLine();
					System.out.println();
					System.out.println("Incorrect input. Try again.");
					System.out.println();
				}
			if(direction == 0)
				return;
			if(direction == 1){
				if(maze[X_Pos][Y_Pos].doorNorth()){
					Y_Pos--;
					decision = true;
				}
			}
			else if(direction == 2){
				if(maze[X_Pos][Y_Pos].doorSouth()){
					Y_Pos++;
					decision = true;
				}
			}
			else if(direction == 3){
				if(maze[X_Pos][Y_Pos].doorEast()){
					X_Pos++;
					decision = true;
				}
			}
			else if(direction == 4){
				if(maze[X_Pos][Y_Pos].doorWest()){
					X_Pos--;
					decision = true;
				}
			}
			else if(direction == 5){
				if(Health<100)
					Health++;
				decision=true;
			}
			else{
				System.out.println("Not an option. Try again.");
			}
			if(!decision){
				System.out.println();
				System.out.println("Not an option. Try again.");
				System.out.println();
			}
			exit_catch = false;
		}
	}
	
	/**
		This method generates how much damage the enemy received from an attack.
		@param eAttack
		@return received
	*/
	public int damageRecieved(int eAttack){
		int x = (int)(Math.random()*20)+eAttack-20;
		int y = (int)(Math.random()*20)+Armor-20;
		int recieved = (x - y > 0) ? x-y : 0;
		Health-=(recieved);
		return recieved;
	}
	
	/**
		This method moves the player in a random allowed direction and returns what the direction was.
		@param maze
		@return direction
	*/
	public String Run(RoomGenerator[][] maze){
		boolean decision = false;
		String direction = "";
		while(!decision){
			int n = (int)(Math.random()*4);
			if(maze[X_Pos][Y_Pos].doorNorth() && n == 0){
				direction = "North";
				decision = true;
				Y_Pos--;
			}
				
			if(maze[X_Pos][Y_Pos].doorSouth() && n == 1){
				direction = "South";
				decision = true;
				Y_Pos++;
			}
			
			if(maze[X_Pos][Y_Pos].doorEast() && n == 2){
				direction = "East";
				decision = true;
				X_Pos++;
			}
			
			if(maze[X_Pos][Y_Pos].doorWest() && n == 3){
				direction = "West";
				decision = true;
				X_Pos--;
			}
		}
		return direction;
	}
	
	/**
		Returns player's health
		@return Health
	*/
	public int getHealth(){
		return Health;
	}
	
	/**
		Returns player's attack
		@return Attack
	*/
	public int getAttack(){
		return Attack;
	}
	
	/**
		Returns player's armor
		@return Armor
	*/
	public int getArmor(){
		return Armor;
	}
	
	/**
		Returns player's X position
		@return X_Pos
	*/
	public int getX(){
		return X_Pos;
	}
	
	/**
		Returns player's Y position
		@return Y_Pos
	*/
	public int getY(){
		return Y_Pos;
	}
	
	/**
		Increase the Attack of the player
		@param n
	*/
	public void addAttack(int n){
		Attack+=n;
	}
	
	/**
		Increase the Attack of the player
		@param n
	*/
	public void addArmor(int n){
		Armor+=n;
	}
	
	/**
		Increase the Attack of the player
		@param n
	*/
	public void addHealth(int n){
		Health+=n;
	}
}