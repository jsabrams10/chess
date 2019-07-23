/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public class Bishop extends Piece {
	
	public Bishop(String color) {
		
		super(color);
		
		type = "Bishop";
		
		if(color.equals("White")){	id = "wB";}
		
		else{						id = "bB";}
	}
	
	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn){
	
		if(whiteTurn && color.equals("Black") || !whiteTurn && color.equals("White")){	return false;}
		
		return verifyDiagonalMove(board, destPos, whiteTurn);	
	}
}