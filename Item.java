import java.lang.Math;
/**
	This class keeps track of an item, position, type, and quality
*/
public class Item{
	private int type, quality, x, y;
	public Item(int maze_size){
		type = (int)(Math.random()*3);
		quality = (int)(Math.random()*5)+1;
		x = (int)(Math.random()*maze_size);
		y = (int)(Math.random()*maze_size);
	}
	public String Update(Player p1){
		String update = "";
		if(type==0){
			p1.addAttack(quality);
			update = "Attack + " + Integer.toString(quality);
		}
		if(type==1){
			p1.addHealth(quality);
			update = "Health + " + Integer.toString(quality);
		}
		if(type==2){
			p1.addArmor(quality);
			update = "Armor + " + Integer.toString(quality);
		}
		x=-1;
		y=-1;
		return update;
	}
	
	/**
		Returns enemy's X position
		@return X_Pos
	*/
	public int getX(){
		return x;
	}
	
	/**
		Returns enemy's Y position
		@return Y_Pos
	*/
	public int getY(){
		return y;
	}
}

