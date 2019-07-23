/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package pieces;

import board.*;

public class King extends Piece {

	public King(String color) {

		super(color);

		type = "King";

		if (color.equals("White")) {
			id = "wK";
		}

		else {
			id = "bK";
		}
	}

	public boolean verifyMove(Piece[][] board, String destPos, boolean whiteTurn) {

		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));

		if (whiteTurn && color.equals("Black") || !whiteTurn && color.equals("White")) {
			return false;
		}

		if (destPosX < 'a' || destPosX > 'h' || destPosY < 1 || destPosY > 8) {
			return false;
		}

		int arrayDestPosX = toArrayPosX(destPosX);
		int arrayDestPosY = toArrayPosY(destPosY);

		if (arrayPosX == arrayDestPosX && arrayPosY == arrayDestPosY) {
			return false;
		}

		if (whiteTurn && Board.spaceChecked(false, arrayDestPosX, arrayDestPosY)) {
			return false;
		}

		else if (!whiteTurn && Board.spaceChecked(true, arrayDestPosX, arrayDestPosY)) {
			return false;
		}

		if (arrayPosX == arrayDestPosX - 1 || arrayPosX == arrayDestPosX + 1) {

			if (arrayPosY == arrayDestPosY) {
				hasMoved = true;
				return true;
			}
		}

		else if (arrayPosY == arrayDestPosY - 1 || arrayPosY == arrayDestPosY + 1) {

			if (arrayPosX == arrayDestPosX) {
				hasMoved = true;
				return true;
			}
		} else if (!hasMoved) { //if king hasnt moved
			if (arrayPosX == arrayDestPosX - 2 && arrayPosY == arrayDestPosY) { //if going two squares to right
				if(Board.board[arrayPosY][arrayPosX+1]==null && Board.board[arrayPosY][arrayPosX+2]==null){ //if there are no pieces between king and rook
					if(!Board.spaceChecked(!whiteTurn, arrayDestPosX, arrayDestPosY)){//if castling doesnt result in check
						//if not under check now->already taken care of in prev if statement
						//if rook hasnt moved->obscure scenario may not implement
						if(Board.board[arrayPosY][7]!=null && Board.board[arrayPosY][7].type.equals("Rook") && Board.board[arrayPosY][7].isAlive){ //if rook exists in right corner and is alive
							return true;

						}
					}
				}
			}
		}
		return false;
	}
}