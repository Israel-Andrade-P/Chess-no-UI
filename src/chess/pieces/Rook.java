package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //Above
        p.setCoords(getPosition().getRow() - 1, getPosition().getColumn());

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        //Left
        p.setCoords(getPosition().getRow(), getPosition().getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        //Right
        p.setCoords(getPosition().getRow(), getPosition().getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        //Down
        p.setCoords(getPosition().getRow() + 1, getPosition().getColumn());

        while (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
            moves[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            moves[p.getRow()][p.getColumn()] = true;
        }

        return moves;
    }

    @Override
    public String toString() {
        return "R";
    }
}