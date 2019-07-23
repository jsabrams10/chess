/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public class Knight extends Piece {
	
	public Knight(String color) {
		
		super(color);
		
		type = "Knight";
		
		if(color.equals("White")){	id = "wN";}
		
		else{						id = "bN";}
	}
	
	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn){
	
		if(whiteTurn && color.equals("Black") || !whiteTurn && color.equals("White")){	return false;}
	
		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));
		
		if(destPosX < 'a' || destPosX > 'h' || 
				destPosY < 1 || destPosY > 8){											return false;}
		
		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);
		
		if(arrayPosX == arrayDestPosX && 
				arrayPosY == arrayDestPosY){											return false;}
		
		if(arrayPosX == arrayDestPosX - 1 || arrayPosX == arrayDestPosX + 1){
			
			if(arrayPosY == arrayDestPosY - 2 || arrayPosY == arrayDestPosY + 2){		return true;}
		}
		
		if(arrayPosX == arrayDestPosX - 2 || arrayPosX == arrayDestPosX + 2){
			
			if(arrayPosY == arrayDestPosY - 1 || arrayPosY == arrayDestPosY + 2){		return true;}
		}
		
		return false;
	}
}