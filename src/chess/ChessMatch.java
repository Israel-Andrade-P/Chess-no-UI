package chess;

import boardgame.Board;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] pieces = new ChessPiece[board.getRow()][board.getColumn()];

        for (int i = 0; i < board.getRow(); i++){
            for (int j = 0; j < board.getColumn(); j++){
                pieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return pieces;
    }
}
