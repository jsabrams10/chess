/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

public class Rook extends Piece {
	
	public Rook(String color) {
		
		super(color);
		
		type = "Rook";
		
		if(color.equals("White")){	id = "wR";}
		
		else{						id = "bR";}
	}
	
	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn){
		
		if(whiteTurn && color.equals("Black") || !whiteTurn && color.equals("White")){	return false;}
		
		return verifyHorizontalMove(board, destPos, whiteTurn);
	}
}