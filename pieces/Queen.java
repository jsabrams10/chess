/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public class Queen extends Piece {
	
	public Queen(String color) {
		
		super(color);
		
		type = "Queen";
		
		if(color.equals("White")){	id = "wQ";}
		
		else{						id = "bQ";}
	}
	
	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn){
		
		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));
		
		if(whiteTurn && color.equals("Black") || 
			!whiteTurn && color.equals("White")){	return false;}
		
		if(destPosX < 'a' || destPosX > 'h' || 
			destPosY < 1 || destPosY > 8){			return false;}
		
		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);
		
		if(arrayPosX == arrayDestPosX && 
				arrayPosY == arrayDestPosY){		return false;}
		
		if(arrayPosX == arrayDestPosX || 
				arrayPosY == arrayDestPosY){		return verifyHorizontalMove(board, destPos, whiteTurn);}
		
		else if(arrayPosX - arrayDestPosX == 
			arrayPosY - arrayDestPosY){				return verifyDiagonalMove(board, destPos, whiteTurn);}
		
		return false;
	}
}