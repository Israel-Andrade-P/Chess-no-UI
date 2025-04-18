package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         p.setCoords(getPosition().getRow() - 2, getPosition().getColumn() - 1);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        p.setCoords(getPosition().getRow() - 2, getPosition().getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() + 2);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         p.setCoords(getPosition().getRow() + 2, getPosition().getColumn() + 1);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        p.setCoords(getPosition().getRow() + 2, getPosition().getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            moves[p.getRow()][p.getColumn()] = true;
        }

         p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() - 2);
         if (getBoard().positionExists(p) && canMove(p)) {
             moves[p.getRow()][p.getColumn()] = true;
         }

        return moves;
    }

    @Override
    public String toString() {
        return "N";
    }
}
