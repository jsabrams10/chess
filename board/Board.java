/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package board;

import pieces.*;

public class Board {

	public static Piece board[][] = new Piece[8][8];

	static boolean check = false;

	public Board(Piece board[][]) {

		super();

		board = Board.board;
	}

	public void setPieces() {

		String color = "White";

		boolean allSet = false;

		while (!allSet) {

			for (int pawnNum = 1; pawnNum <= 8; pawnNum++) {

				Piece pawn = new Pawn(color);

				if (pawnNum == 1) {
					pawn.setPosX('a');
				}

				else if (pawnNum == 2) {
					pawn.setPosX('b');
				}

				else if (pawnNum == 3) {
					pawn.setPosX('c');
				}

				else if (pawnNum == 4) {
					pawn.setPosX('d');
				}

				else if (pawnNum == 5) {
					pawn.setPosX('e');
				}

				else if (pawnNum == 6) {
					pawn.setPosX('f');
				}

				else if (pawnNum == 7) {
					pawn.setPosX('g');
				}

				else {
					pawn.setPosX('h');
				}

				if (color.equals("White")) {
					pawn.setPosY(2);
				}

				else {
					pawn.setPosY(7);
				}

				pawn.updateArrayPos();

				board[pawn.getArrayPosY()][pawn.getArrayPosX()] = pawn;
			}

			for (int rookNum = 1; rookNum <= 2; rookNum++) {

				Piece rook = new Rook(color);

				if (rookNum == 1) {
					rook.setPosX('a');
				}

				else {
					rook.setPosX('h');
				}

				if (color.equals("White")) {
					rook.setPosY(1);
				}

				else {
					rook.setPosY(8);
				}

				rook.updateArrayPos();

				board[rook.getArrayPosY()][rook.getArrayPosX()] = rook;
			}

			for (int knightNum = 1; knightNum <= 2; knightNum++) {

				Piece knight = new Knight(color);

				if (knightNum == 1) {
					knight.setPosX('b');
				}

				else {
					knight.setPosX('g');
				}

				if (color.equals("White")) {
					knight.setPosY(1);
				}

				else {
					knight.setPosY(8);
				}

				knight.updateArrayPos();

				board[knight.getArrayPosY()][knight.getArrayPosX()] = knight;
			}

			for (int bishopNum = 1; bishopNum <= 2; bishopNum++) {

				Piece bishop = new Bishop(color);

				if (bishopNum == 1) {
					bishop.setPosX('c');
				}

				else {
					bishop.setPosX('f');
				}

				if (color.equals("White")) {
					bishop.setPosY(1);
				}

				else {
					bishop.setPosY(8);
				}

				bishop.updateArrayPos();

				board[bishop.getArrayPosY()][bishop.getArrayPosX()] = bishop;
			}

			Piece queen = new Queen(color);

			queen.setPosX('d');

			if (color.equals("White")) {
				queen.setPosY(1);
			}

			else {
				queen.setPosY(8);
			}

			queen.updateArrayPos();

			board[queen.getArrayPosY()][queen.getArrayPosX()] = queen;

			Piece king = new King(color);

			king.setPosX('e');

			if (color.equals("White")) {
				king.setPosY(1);
			}

			else {
				king.setPosY(8);
			}

			king.updateArrayPos();

			board[king.getArrayPosY()][king.getArrayPosX()] = king;

			if (color.equals("White")) {
				color = "Black";
			}

			else {
				allSet = true;
			}
		}

		return;
	}
	
/**
 *  @param pos - String - current location of the piece - of the form "e2"
 *
 *	@param destPos - String - destination of the piece - of the form "e4"
 *
 *	@param whiteTurn - boolean - whether or not it's White's turn or not
 *
 *	@return - boolean - whether or not the move was accomplished
 *
 */
	public boolean movePiece(String pos, String destPos, boolean whiteTurn) {

		char posX = pos.charAt(0);
		int posY = Character.getNumericValue(pos.charAt(1));

		if (posX < 'a' || posX > 'h' || posY < 1 || posY > 8) {
			return false;
		}

		int arrayPosX = Piece.toArrayPosX(posX);
		int arrayPosY = Piece.toArrayPosY(posY);

		char destPosX = destPos.charAt(0);
		int destPosY = Character.getNumericValue(destPos.charAt(1));

		int arrayDestPosX = Piece.toArrayPosX(destPosX);
		int arrayDestPosY = Piece.toArrayPosY(destPosY);

		if (board[arrayPosY][arrayPosX] != null && board[arrayPosY][arrayPosX].verifyMove(board, destPos, whiteTurn)) {

			if (board[arrayDestPosY][arrayDestPosX] != null) {

				if (whiteTurn && board[arrayDestPosY][arrayDestPosX].color == "White") {

					return false;
				}

				else if (!whiteTurn && board[arrayDestPosY][arrayDestPosX].color == "Black") {

					return false;
				}
			}

			board[arrayDestPosY][arrayDestPosX] = board[arrayPosY][arrayPosX];

			board[arrayPosY][arrayPosX] = null;

			board[arrayDestPosY][arrayDestPosX].setPosX(destPosX);
			board[arrayDestPosY][arrayDestPosX].setPosY(destPosY);

			board[arrayDestPosY][arrayDestPosX].updateArrayPos();
			int posYtemp;
			if (whiteTurn){
				posYtemp=1;
			}
			else{
				posYtemp=8;

			}
			//if castling right
			if(board[arrayDestPosY][arrayDestPosX].type.equals("King") && arrayDestPosX-2 ==arrayPosX ){ //king moved 2 pieces to right
				//move rook to kings spot
				board[arrayPosY][arrayPosX+1] = board[arrayPosY][7];
				board[arrayPosY][7]=null;
				board[arrayPosY][arrayPosX+1].setPosX('f');
				board[arrayPosY][arrayPosX+1].setPosY(posYtemp);
				board[arrayPosY][arrayPosX+1].updateArrayPos();
			}
			//if castling left
			if(board[arrayDestPosY][arrayDestPosX].type.equals("King") && arrayDestPosX+2 ==arrayPosX ){ //king moved 2 pieces to right
				//move rook to kings spot
				board[arrayPosY][arrayPosX-1] = board[arrayPosY][7];
				board[arrayPosY][0]=null;
				board[arrayPosY][arrayPosX-1].setPosX('d');
				board[arrayPosY][arrayPosX-1].setPosY(posYtemp);
				board[arrayPosY][arrayPosX-1].updateArrayPos();
			}

			return true;
		}

		else {
			return false;
		}
	}

	public static int[] getKingPos(String color) {

		int[] kingPos = new int[2];

		for (int row = 0; row <= 7; row++) {

			for (int col = 0; col <= 7; col++) {

				if (board[row][col] != null && board[row][col].type.equals("King")
						&& board[row][col].color.equals(color)) {

					kingPos[0] = row;
					kingPos[1] = col;

					return kingPos;
				}
			}
		}

		return null;
	}


/**
 *  @param whiteAttack - boolean - whether or not White is attacking (when White is attacking, it's Black's turn)
 *
 *	@param arrayPosX - int - row index of the space in question in the array
 *
 *	@param arrayPosY - int - col index of the space in question in the array
 *
 *	@return - boolean - whether or not the space is "checked" (if checked, a "defending" King could not move there)
 *
 */
	public static boolean spaceChecked(boolean whiteAttack, int arrayPosX, int arrayPosY) {

		if (arrayPosX < 0 || arrayPosX > 7 || arrayPosY < 0 || arrayPosY > 7) {
			return false;
		}

		int arrayTestPosX;
		int arrayTestPosY;

		if (whiteAttack) {

			arrayTestPosX = arrayPosX - 1;
			arrayTestPosY = arrayPosY + 1;

			if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
					&& board[arrayTestPosY][arrayTestPosX] != null &&

					(board[arrayTestPosY][arrayTestPosX].id.equals("wp")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wB")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wK"))) {
				return true;
			}

			arrayTestPosX = arrayPosX + 1;

			if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
					&& board[arrayTestPosY][arrayTestPosX] != null &&

					(board[arrayTestPosY][arrayTestPosX].id.equals("wp")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wB")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wK"))) {
				return true;
			}
		}

		else {

			arrayTestPosX = arrayPosX - 1;
			arrayTestPosY = arrayPosY - 1;

			if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
					&& board[arrayTestPosY][arrayTestPosX] != null &&

					(board[arrayTestPosY][arrayTestPosX].id.equals("bp")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("bB")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("bK"))) {
				return true;
			}

			arrayTestPosX = arrayPosX + 1;

			if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
					&& board[arrayTestPosY][arrayTestPosX] != null &&

					(board[arrayTestPosY][arrayTestPosX].id.equals("bp")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("bB")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")
							|| board[arrayTestPosY][arrayTestPosX].id.equals("wK"))) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX - 2;
		arrayTestPosY = arrayPosY - 1;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX - 2;
		arrayTestPosY = arrayPosY + 1;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX - 1;
		arrayTestPosY = arrayPosY - 2;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX - 1;
		arrayTestPosY = arrayPosY + 2;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX + 1;
		arrayTestPosY = arrayPosY - 2;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX + 1;
		arrayTestPosY = arrayPosY + 2;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX + 2;
		arrayTestPosY = arrayPosY - 1;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX + 2;
		arrayTestPosY = arrayPosY + 1;

		if (arrayTestPosX >= 0 && arrayTestPosX <= 7 && arrayTestPosY >= 0 && arrayTestPosY <= 7
				&& board[arrayTestPosY][arrayTestPosX] != null) {

			if (whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("wN")) {
				return true;
			}

			else if (!whiteAttack && board[arrayTestPosY][arrayTestPosX].id.equals("bN")) {
				return true;
			}
		}

		arrayTestPosX = arrayPosX - 1;
		arrayTestPosY = arrayPosY;

		while (arrayTestPosX >= 0) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {
				arrayTestPosX--;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX + 1;

		while (arrayTestPosX <= 7) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {
				arrayTestPosX++;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX;
		arrayTestPosY = arrayPosY - 1;

		while (arrayTestPosY >= 0) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {
				arrayTestPosY--;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosY = arrayPosY + 1;

		while (arrayTestPosY <= 7) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {
				arrayTestPosY++;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bR")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX - 1;
		arrayTestPosY = arrayPosY - 1;

		while (arrayTestPosX >= 0 && arrayTestPosY >= 0) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {

				arrayTestPosX--;
				arrayTestPosY--;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX - 1;
		arrayTestPosY = arrayPosY + 1;

		while (arrayTestPosX >= 0 && arrayTestPosY <= 7) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {

				arrayTestPosX--;
				arrayTestPosY++;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX + 1;
		arrayTestPosY = arrayPosY + 1;

		while (arrayTestPosX <= 7 && arrayTestPosY <= 7) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {

				arrayTestPosX++;
				arrayTestPosY++;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		arrayTestPosX = arrayPosX + 1;
		arrayTestPosY = arrayPosY - 1;

		while (arrayTestPosX <= 7 && arrayTestPosY >= 0) {

			if (board[arrayTestPosY][arrayTestPosX] == null) {

				arrayTestPosX++;
				arrayTestPosY--;
			}

			else if (whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("wB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("wQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("wK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}

			else if (!whiteAttack) {

				if (board[arrayTestPosY][arrayTestPosX].id.equals("bB")
						|| board[arrayTestPosY][arrayTestPosX].id.equals("bQ")) {
					return true;
				}

				else if (board[arrayTestPosY][arrayTestPosX].id.equals("bK") && arrayPosX - arrayTestPosX <= 1
						&& arrayPosY - arrayTestPosY <= 1) {
					return true;
				}

				else {
					break;
				}
			}
		}

		return false;
	}

	public boolean inCheck(String color) {

		int[] kingPos = getKingPos(color);

		int arrayKingPosY = kingPos[0];
		int arrayKingPosX = kingPos[1];
		isCheckMate("White");

		return spaceChecked(!color.equals("White"), arrayKingPosX, arrayKingPosY);
	}

	public boolean isCheckMate(String color) {
		// check if any of kings moves are valid, and if space checked on that
		// move is false
		int[] kingPos = getKingPos(color);
		int arrayKingPosY = kingPos[0];
		int arrayKingPosX = kingPos[1];
		// loop through every piece, loop through every square and move there if
		// valid. if after loop the result of space checked is still true, you
		// have a checkmate
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (board[i][j] != null) {
					// System.out.print(board[i][j].color +" ");

					for (int ii = 0; ii <= 7; ii++) {
						for (int jj = 0; jj <= 7; jj++) {
							Piece boardTemp[][] = board;
							movePiece(board[i][j].getPos(), toStringPos(ii, jj), color.equals("White"));
							if (!spaceChecked(color.equals("Black"), arrayKingPosX, arrayKingPosY)) // king
																									// not
																									// in
																									// check
																									// after
																									// move
							{
								board = boardTemp;
								return false;
							}
							// undo move. go back to prev state of board
							board = boardTemp;
						}
					}	
				}
			}
		}

		return true;
	}

	public String toStringPos(int i, int j) {
		String result = "";
		switch (i) {
		case 0:
			result += 'a';
		case 1:
			result += 'b';
		case 2:
			result += 'c';
		case 3:
			result += 'd';
		case 4:
			result += 'e';
		case 5:
			result += 'f';
		case 6:
			result += 'g';
		case 7:
			result += 'h';
		default:
		}
		switch (j) {
		case 0:
			result += '1';
		case 1:
			result += '2';
		case 2:
			result += '3';
		case 3:
			result += '4';
		case 4:
			result += '5';
		case 5:
			result += '6';
		case 6:
			result += '7';
		case 7:
			result += '8';
		}

		return result;

	}

	public static boolean isStaleMate(String color) {
		return false;
	}

	public static void promote() {
		return;
	}

	public void printBoard() {

		for (int row = 0; row < 8; row++) {

			for (int col = 0; col < 8; col++) {

				if (board[row][col] != null) {
					System.out.print(board[row][col].id + " ");
				}

				else {

					if ((row + col) % 2 == 1) {
						System.out.print("## ");
					}

					else {
						System.out.print("   ");
					}
				}

				if (col == 7) {
					System.out.println(8 - row);
				}
			}
		}

		System.out.println(" a  b  c  d  e  f  g  h\n");

		return;
	}
}
