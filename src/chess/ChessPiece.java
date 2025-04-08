package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;


    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }  

    public ChessPosition getChessPosition () {
        return ChessPosition.fromPosition(getPosition());
    }

    public boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p.getColor() != color;
    }
}
