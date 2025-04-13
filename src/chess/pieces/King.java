package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0; 
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //Above
        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         //Down
         p.setCoords(getPosition().getRow() + 1, getPosition().getColumn());
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        //Left
        p.setCoords(getPosition().getRow(), getPosition().getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         //Right
         p.setCoords(getPosition().getRow(), getPosition().getColumn() + 1);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        //North East
        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         //North West
         p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() + 1);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

          //South East
        p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         //South West
         p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() + 1);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

         //Special move Rook Castling
         if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            //Castling kingside rook
            Position posR1 = new Position(getPosition().getRow(), getPosition().getColumn() + 3);
            if (testRookCastling(posR1)) {
                Position p1 = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                Position p2 = new Position(getPosition().getRow(), getPosition().getColumn() + 2);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null) {
                    moves[getPosition().getRow()][getPosition().getColumn() + 2] = true;
                }
            }

             //Castling queenside rook
             Position posR2 = new Position(getPosition().getRow(), getPosition().getColumn() - 4);
             if (testRookCastling(posR2)) {
                 Position p1 = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                 Position p2 = new Position(getPosition().getRow(), getPosition().getColumn() - 2);
                 Position p3 = new Position(getPosition().getRow(), getPosition().getColumn() - 3);
                 if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null && getBoard().getPiece(p3) == null) {
                     moves[getPosition().getRow()][getPosition().getColumn() - 2] = true;
                 }
             }
         }

        return moves;
    }

    @Override
    public String toString() {
        return "K";
    }
}
