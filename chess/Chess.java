/**
 *  @author John Abrams (jsa109)
 *  @author Ruslan Volyar (rv379)
 */

package chess;

import board.Board;

import pieces.Piece;

public class Chess {

	public static void main(String[] args) {

		Board board = new Board(new Piece[8][8]);
		
		board.setPieces();
		
		boolean gameOn = true;
		boolean whiteTurn = true;
		boolean drawFlag = false;
		
		while(gameOn){
		
			board.printBoard();
			
			if(whiteTurn){
				
				if(board.inCheck("White")){
		
					if(board.isCheckMate("White")){
						System.out.println("Black Wins\n");
						gameOn=false;
						break;
					}
					else{
						System.out.println("Check\n");
					}
				}
				
				System.out.print("White's move:");
			}
			
			else{
				
				if(board.inCheck("Black")){
									
						if(board.isCheckMate("Black")){
							
							System.out.println("White Wins\n");
							gameOn=false;
							break;
						
						}
						else{
							System.out.println("Check\n");
						}
				}
				
				System.out.print("Black's move:");
			}
			
			String input = IO.readLine();
			
			System.out.println("");
			
			input = input.trim();
			
			if(input.length() >= 6 && input.contains("resign")){
				
				if(whiteTurn){
					
					System.out.println("Black wins\n");
					return;
				}
				
				else{
					
					System.out.println("White wins\n");
					return;
				}
			}
			
			if((input.contains("draw?") || input.contains("Draw?")) && !drawFlag){		drawFlag = true;}
			
			else if((input.contains("draw") || input.contains("Draw")) && drawFlag){	return;}
			
			String srcPos = input.substring(0, 2);
			
			input = input.substring(3);
			input = input.trim();
			
			String destPos = input.substring(0, 2);
			
			boolean illegal = false;
			
			if(!board.movePiece(srcPos, destPos, whiteTurn)){
				
				illegal = true;
				
				System.out.println("Illegal move, try again\n");
			}
			
			if(!illegal && whiteTurn){			whiteTurn = false;}
			
			else if(!illegal && !whiteTurn){	whiteTurn = true;}
		}
		
		return;
	}
}


