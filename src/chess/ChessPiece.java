package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;
    private int moveCount;


    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition () {
        return ChessPosition.fromPosition(getPosition());
    }

    public boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p.getColor() != color;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public Color getColor() {
        return color;
    }  

    public int getMoveCount() {
        return moveCount;
    }
}
