package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setCoords(p.getRow() - 1, p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setCoords(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        return moves;
    }

    @Override
    public String toString() {
        return "B";
    }
}