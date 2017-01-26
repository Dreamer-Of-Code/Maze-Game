import java.util.Scanner;
import java.lang.Math;

/**
	This class is responsible for the setup of every other class and having all the objects interact.
*/
public class Game{
	
	private Scanner in;
	private Player p1;
	private Enemy[] enemy;
	private RoomGenerator[][] maze;
	private Item[] item;
	private int maze_size, end_x, end_y, enemyCount;
	
	public Game(){
		in = new Scanner(System.in);
		System.out.print("Enter in the size of the maze: ");
		maze_size = in.nextInt(); //This variable declares the size of the maze. The number of rooms is maze_size squared
		maze = new RoomGenerator[maze_size][maze_size];
		
		end_x = -1;
		end_y = -1;
		
		//Generates Player with random position and semi-random(less room for variation) stats
		p1 = new Player((int)((Math.random()*16)+5),(int)((Math.random()*51)+50),(int)((Math.random()*16)+5),(int)((Math.random()*maze_size)),(int)((Math.random()*maze_size)));
		
		
		
		//Generates rooms without going outside of array bounds or passing nullPointerExceptions
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Creating rooms...");
		
		
		//Generates blank slate rooms (This is what stops the code from throwing nullPointerExceptions
		for(int i = 0; i < maze_size; i++){
			for(int j = 0; j < maze_size; j++){
				maze[j][i] = new RoomGenerator();
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Generating doors...");
		
		//These two for loops are what do the "real" generation of the rooms
		for(int i = 0; i < maze_size; i++){
			for(int j = 0; j < maze_size; j++){
				if(i>0){//If minus one goes outside of array bounds
					if(j>0)//If minus one goes outside of array bounds
						maze[j][i] = new RoomGenerator(3, 1, maze[j][i-1].doorSouth(), maze[j-1][i].doorEast(), maze, j, i, maze_size);
					else
						maze[j][i] = new RoomGenerator(3, 1, maze[j][i-1].doorSouth(), maze[j][i].doorEast(), maze, j, i, maze_size);
				}
				else{
					if(j>0){//If minus one goes outside of array bounds
						maze[j][i] = new RoomGenerator(3, 1, maze[j][i].doorSouth(), maze[j-1][i].doorEast(), maze, j, i, maze_size);
					}
					else{
						maze[j][i] = new RoomGenerator(3, 1, maze[j][i].doorSouth(), maze[j][i].doorEast(), maze, j, i, maze_size);
					}
				}
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Checking rooms for faults...");
		
		//These two for loops are what makes sure that there are no problems with the doors
		for(int i = 0; i < maze_size; i++){
			for(int j = 0; j < maze_size; j++){
				if(maze[j][i].doorSouth() && i < maze_size-1)
					maze[j][i+1].change_doorNorth();
				if(maze[j][i].doorNorth() && i > 0)
					maze[j][i-1].change_doorSouth();
				if(maze[j][i].doorEast() && j < maze_size-1)
					maze[j+1][i].change_doorWest();
				if(maze[j][i].doorWest() && j > 0)
					maze[j-1][i].change_doorEast();
				
				if(i == 0  &&  maze[j][i].doorNorth()){
					maze[j][i].false_doorNorth();
				}
				if(i == maze_size-1  &&  maze[j][i].doorSouth()){
					maze[j][i].false_doorSouth();
				}
				if(j == maze_size-1  &&  maze[j][i].doorEast()){
					maze[j][i].false_doorEast();
				}
				if(j == 0  &&  maze[j][i].doorWest()){
					maze[j][i].false_doorWest();
				}
			}
		}
		
		//Room Generation end
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//Generates random amount of enemies with random stats
		System.out.println("Generating enemies and items...");
		
		enemyCount = (int)((Math.random()*((maze_size*maze_size)/4))+((maze_size*maze_size)/4));
		enemy = new Enemy[enemyCount];
		item = new Item[enemyCount];
		for(int i = 0; i<enemyCount; i++){
			item[i] = new Item(maze_size);
			enemy[i] = new Enemy((int)((Math.random()*30)+1),(int)((Math.random()*120)+1),(int)((Math.random()*30)+1),i,maze,maze_size);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//Chooses the room that is the exit
		System.out.println("Choosing maze exit point...");
		
		end_x = (int)(Math.random() * maze_size);
		end_y = (int)(Math.random() * maze_size);
		while(p1.getX() == end_x  &&  p1.getY() == end_y){
			end_x = (int)(Math.random() * maze_size);
			end_y = (int)(Math.random() * maze_size);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("(Hint: There are " + enemyCount + " Items and Enemies)");
		
		System.out.println();
		System.out.println();
	}
	
	
	
	/**
		This method is the main combat sequence of the game. It determines death, running, and uses user input to determine what to do next.
		@param Enemy enemy, Player p1, Roomgenerator[][] maze
	*/
	public void combatSequence(Enemy enemy){
		
		int decision = 0;
		int original = enemy.getHealth();
		while(enemy.getHealth() > original/4  &&  p1.getHealth() > 0 && decision!=2){ //While both enemy and player are alive
		
			System.out.println();
			System.out.println("Enemy Stats:   Health: " + enemy.getHealth() + " Attack: " + enemy.getAttack() + " Armor: " + enemy.getArmor());
			System.out.println("Your  Stats:   Health: " + p1.getHealth() + " Attack: " + p1.getAttack() + " Armor: " + p1.getArmor());
			
			boolean exit_catch = false;
			
			while(!exit_catch){ //While the input is not bueno
				try{
					System.out.println("Attack: 1");
					System.out.println("Run: 2 \n(WARNING: RUNNING SENDS YOU IN A RANDOM DIRECTION AND ALLOWS THE ENEMY TO ATTACK YOU)");
					System.out.print("Enter your decision: ");
					decision = in.nextInt();
					in.nextLine();
					exit_catch = true; //If it gets here before throwing, the output is bueno
				}
				catch(Exception e){
					in.nextLine();
					System.out.println();
					System.out.println("Incorrect input. Try again.");
					System.out.println();
				}
			}
			
			System.out.println();
			if(decision == 1){
				System.out.println("You did " + enemy.damageRecieved(p1.getAttack(),maze));
				System.out.println("You received: " + p1.damageRecieved(enemy.getAttack()));
			}
			if(decision == 2){
				System.out.println("You received: " + p1.damageRecieved(enemy.getAttack()));
				System.out.println("You ran " + p1.Run(maze));
			}
			if(enemy.getHealth() <= 0){
				System.out.println();
				System.out.println("Enemy is dead.");
			}
			else if(enemy.getHealth() < original/4){
				System.out.println();
				System.out.println("Enemy fled");
			}
		}
	}
	
	
	
	/**
		This method is what runs the game.
	*/
	public void runGame(){
		
		while((p1.getX()!=end_x || p1.getY()!=end_y)  &&  p1.getHealth() > 0){
			
			
			p1.Player_Move(maze);
			
			for(int i = 0; i<enemyCount; i++)
				enemy[i].Enemy_Move(maze);
			
			if(maze[p1.getX()][p1.getY()].OccupyID() >= 0)
				if(maze[p1.getX()][p1.getY()].isOccupied())
					combatSequence(enemy[maze[p1.getX()][p1.getY()].OccupyID()]);
			
			if(p1.getHealth() > 0)
				for(int i = 0; i<enemyCount; i++){
					if(p1.getX() == item[i].getX()  &&  p1.getY() == item[i].getY()){
						System.out.println();
						System.out.println();
						System.out.println("There is an item in the room " + item[i].Update(p1));
						System.out.println();
						System.out.println();
					}
				}
		}
		
		if(p1.getHealth() <= 0){
			System.out.println();
			System.out.println();
			System.out.println("You died... (To make the game easier, make the maze smaller.)\nExit at: ("+end_x+","+end_y+")\nYour final position:("+p1.getX()+","+p1.getY()+")");
			System.out.println();
			System.out.println();
		}
		else{
			System.out.println();
			System.out.println();
			System.out.println("You found your way out!!! Congratulations!!! \nExit at: ("+end_x+","+end_y+")");
			System.out.println();
			System.out.println();
		}
		
	}
}
