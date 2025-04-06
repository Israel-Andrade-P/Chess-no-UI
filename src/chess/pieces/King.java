package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) {
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

        return moves;
    }

    @Override
    public String toString() {
        return "K";
    }
}
