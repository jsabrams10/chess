/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public abstract class Piece {

	public String color;
	public String type;
	public String id;
	
	protected char posX;
	protected int posY;
	
	protected int arrayPosX;
	protected int arrayPosY;
	
	public boolean isAlive;
	public boolean hasMoved;
	
	public Piece(String color){
		
		this.color = color;
		
		this.isAlive = true;
		this.hasMoved = false;
	}
	
	/**
	 * @param posX
	 * @return Returns array position of character
	 */
	public static int toArrayPosX(char posX){
		
		if(posX == 'a'){		return 0;}
		
		else if(posX == 'b'){	return 1;}
		
		else if(posX == 'c'){	return 2;}
		
		else if(posX == 'd'){	return 3;}
		
		else if(posX == 'e'){	return 4;}
		
		else if(posX == 'f'){	return 5;}
		
		else if(posX == 'g'){	return 6;}
		
		else if(posX == 'h'){	return 7;}
		
		return -1;
	}
	
	public static int toArrayPosY(int posY){	return 8 - posY;}
	
	/**
	 * @param
	 */
	public void updateArrayPos(){
		
		arrayPosX = toArrayPosX(posX);
		arrayPosY = toArrayPosY(posY);
		
		return;
	}
	
	public char getPosX(){		return posX;}
	
	public int getPosY(){		return posY;}
	
	public int getArrayPosX(){	return arrayPosX;}
	
	public int getArrayPosY(){	return arrayPosY;}
	
	public String getPos(){		return posX + posY + "";}
	
	public void setPosX(char newPos){
		
		posX = newPos;
		return;
	}
	
	public void setPosY(int newPos){
		
		posY = newPos;
		return;
	}

	public abstract boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn);
	
	protected boolean verifyHorizontalMove(Piece[][] board, String destPos, boolean whiteTurn){
		
		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));
		
		if(destPosX < 'a' || destPosX > 'h' || 
				destPosY < 1 || destPosY > 8){		return false;}
		
		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);
		
		if(arrayPosX == arrayDestPosX && 
			arrayPosY == arrayDestPosY){			return false;}
		
		if(arrayPosX != arrayDestPosX &&
			arrayPosY != arrayDestPosY){			return false;}
		
		String destDirection = null;
		
		if(arrayPosX < arrayDestPosX){
			
			if(arrayPosY != arrayDestPosY){			return false;}
			
			else{	destDirection = "Right";}
		}
		
		else if(arrayPosX > arrayDestPosX){
			
			if(arrayPosY != arrayDestPosY){			return false;}
			
			else{	destDirection = "Left";}
		}
		
		else{
			
			if(arrayPosY < arrayDestPosY){	destDirection = "Up";}
			
			else{							destDirection = "Down";}
		}
		
		int arrayTestPosX = arrayPosX;
		int arrayTestPosY = arrayPosY;
		
		switch(destDirection){
		
			case "Right":

				arrayTestPosX++;
				
				while(arrayTestPosX <= 7){
					
					if(arrayTestPosX == arrayDestPosX){				return true;}
					
					if(board[arrayPosY][arrayTestPosX] == null){
						
						arrayTestPosX++;
					}
				}
				
				break;
			
			case "Left":
		
				arrayTestPosX--;
				
				while(arrayTestPosX >= 0){
					
					if(arrayTestPosX == arrayDestPosX){				return true;}
					
					if(board[arrayPosY][arrayTestPosX] == null){
						
						arrayTestPosX--;
					}
				}
				
				break;

			case "Up":
		
				arrayTestPosY--;
				
				while(arrayTestPosY >= 0){
					
					if(arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayPosX] == null){
						
						arrayTestPosY--;
					}
				}
				
				break;

			case "Down":
				
				arrayTestPosY++;
				
				while(arrayTestPosY <= 7){
					
					if(arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayPosX] == null){
						
						arrayTestPosY++;
					}
				}
				
				break;
		}
		
		return false;
	}
	
	protected boolean verifyDiagonalMove(Piece[][] board, String destPos, boolean whiteTurn){
		
		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));
		
		if(destPosX < 'a' || destPosX > 'h' || 
			destPosY < 1 || destPosY > 8){			return false;}
		
		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);
		
		if(arrayPosX == arrayDestPosX && 
			arrayPosY == arrayDestPosY){			return false;}
		
		if(arrayPosX - arrayDestPosX !=
			arrayPosY - arrayDestPosY &&
			arrayPosX - arrayDestPosX != 
			-1 * (arrayPosY - arrayDestPosY)){		return false;}
		
		String destDirection = null;
		
		if(arrayPosX < arrayDestPosX){
			
			if(arrayPosY < arrayDestPosY){	destDirection = "BotRight";}
			
			else{	destDirection = "TopRight";}
		}
		
		else{
			
			if(arrayPosY < arrayDestPosY){	destDirection = "BotLeft";}
			
			else{	destDirection = "TopLeft";}
		}
		
		int arrayTestPosX = arrayPosX;
		int arrayTestPosY = arrayPosY;
		
		switch(destDirection){
		
			case "TopRight":

				arrayTestPosX++;
				arrayTestPosY--;
				
				while(arrayTestPosX <= 7 && arrayTestPosY >= 0){
					
					if(arrayTestPosX == arrayDestPosX && 
							arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayTestPosX] == null){
						
						arrayTestPosX++;
						arrayTestPosY--;
					}
				}
				
				break;
			
			case "BotRight":
		
				arrayTestPosX++;
				arrayTestPosY++;
				
				while(arrayTestPosX <= 7 && arrayTestPosY <= 7){
					
					if(arrayTestPosX == arrayDestPosX && 
							arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayTestPosX] == null){
						
						arrayTestPosX++;
						arrayTestPosY++;
					}
				}
				
				break;

			case "BotLeft":
		
				arrayTestPosX--;
				arrayTestPosY++;
				
				while(arrayTestPosX >= 0 && arrayTestPosY <= 7){
					
					if(arrayTestPosX == arrayDestPosX && 
							arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayTestPosX] == null){
						
						arrayTestPosX--;
						arrayTestPosY++;
					}
				}
				
				break;

			case "TopLeft":
				
				arrayTestPosX--;
				arrayTestPosY--;
				
				while(arrayTestPosX >= 0 && arrayTestPosY >= 0){
					
					if(arrayTestPosX == arrayDestPosX && 
							arrayTestPosY == arrayDestPosY){				return true;}
					
					if(board[arrayTestPosY][arrayTestPosX] == null){
						
						arrayTestPosX--;
						arrayTestPosY--;
					}
				}
				
				break;
		}
		
		return false;
	}
	
	public boolean equals(Object o){	return false;}
	
	public String toString(){			return color + " " + type + " @ " + posX + posY;}
}
