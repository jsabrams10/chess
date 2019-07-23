/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public class Pawn extends Piece {
	
	private boolean twoSteppedLastTurn = false;
	
	public Pawn(String color) {
		
		super(color);
		
		type = "Pawn";
		
		if(color.equals("White")){	id = "wp";}
		
		else{						id = "bp";}
	}

	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn){
		
		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));
		
		if(whiteTurn && color.equals("Black") || !whiteTurn && color.equals("White")){	return false;}
		
		if(destPosX < 'a' || destPosX > 'h' || destPosY < 1 || destPosY > 8){			return false;}
		
		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);
		
		if(arrayPosX == arrayDestPosX && arrayPosY == arrayDestPosY){					return false;}
		
		if(color == "White"){
		
			if(arrayPosX == arrayDestPosX){
				
				if(arrayPosY == arrayDestPosY + 1 && 
					board[arrayDestPosY][arrayDestPosX] == null){
					
					if(hasMoved == false){				hasMoved = true;}
					
					if(twoSteppedLastTurn == true){		twoSteppedLastTurn = false;}
					
					return true;
				}
				
				else if(arrayPosY == arrayDestPosY + 2 && 
					board[arrayDestPosY][arrayDestPosX] == null && !hasMoved){
					
					hasMoved = true;
					
					twoSteppedLastTurn = true;
					
					return true;
				}
			}
			
			else if((arrayPosX == arrayDestPosX + 1 || 
				arrayPosX == arrayDestPosX - 1) && arrayPosY == arrayDestPosY + 1){
					
				if(board[arrayDestPosY][arrayDestPosX] != null){
						
					if(hasMoved == false){				hasMoved = true;}
						
					if(twoSteppedLastTurn == true){		twoSteppedLastTurn = false;}
						
					return true;
				}
						
				else if(board[arrayPosY][arrayDestPosX].type == "Pawn"){
						
					Pawn temp = (Pawn)board[arrayPosY][arrayDestPosX];
					
					if(temp.twoSteppedLastTurn){
						
						if(hasMoved == false){			hasMoved = true;}
							
						if(twoSteppedLastTurn == true){	twoSteppedLastTurn = false;}
						
						board[arrayPosY][arrayDestPosX] = null;
						
						return true;
					}
				}
			}
		}
		
		else{
			
			if(arrayPosX == arrayDestPosX){
				
				if(arrayPosY == arrayDestPosY - 1 && 
					board[arrayDestPosY][arrayDestPosX] == null){
					
					if(hasMoved == false){				hasMoved = true;}
					
					if(twoSteppedLastTurn == true){		twoSteppedLastTurn = false;}
					
					return true;
				}
				
				else if(arrayPosY == arrayDestPosY - 2 && 
					board[arrayDestPosY][arrayDestPosX] == null && !hasMoved){
					
					hasMoved = true;
					
					twoSteppedLastTurn = true;
					
					return true;
				}
			}
			
			else if((arrayPosX == arrayDestPosX + 1 || 
				arrayPosX == arrayDestPosX - 1) && arrayPosY == arrayDestPosY - 1){
				
				if(board[arrayDestPosY][arrayDestPosX] != null){
					
					if(hasMoved == false){				hasMoved = true;}
					
					if(twoSteppedLastTurn == true){		twoSteppedLastTurn = false;}
					
					return true;
				}
					
				else if(board[arrayPosY][arrayDestPosX].type == "Pawn"){
					
					Pawn temp = (Pawn)board[arrayPosY][arrayDestPosX];
					
					if(temp.twoSteppedLastTurn){
					
						if(hasMoved == false){			hasMoved = true;}
						
						if(twoSteppedLastTurn == true){	twoSteppedLastTurn = false;}
						
						board[arrayPosY][arrayDestPosX] = null;
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		
		return color + " " + type + " @ " + posX + posY;
	}
}


