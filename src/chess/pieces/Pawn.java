package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);
        

        if (getColor() == Color.WHITE) {
            p.setCoords(getPosition().getRow() - 1, getPosition().getColumn());
            if (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() - 2, getPosition().getColumn());
            Position p2 = new Position(getPosition().getRow() - 1, getPosition().getColumn());
            if (getBoard().positionExists(p) && !getBoard().isTherePiece(p)  && getBoard().positionExists(p2) && !getBoard().isTherePiece(p2) && getMoveCount() == 0) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() - 1, getPosition().getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            //Special move EnPassant
            if (getPosition().getRow() == 3) {
                Position left = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }
        else {
            p.setCoords(getPosition().getRow() + 1, getPosition().getColumn());
            if (getBoard().positionExists(p) && !getBoard().isTherePiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() + 2, getPosition().getColumn());
            Position p2 = new Position(getPosition().getRow() + 1, getPosition().getColumn());
            if (getBoard().positionExists(p) && !getBoard().isTherePiece(p)  && getBoard().positionExists(p2) && !getBoard().isTherePiece(p2) && getMoveCount() == 0) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            p.setCoords(getPosition().getRow() + 1, getPosition().getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }

            //Special move EnPassant
            if (getPosition().getRow() == 4) {
                Position left = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return "P";
    }
}
