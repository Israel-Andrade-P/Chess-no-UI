package boardgame;

public class Piece {
    private Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    protected Position getPosition() {
        return position;
    }

    protected Board getBoard() {
        return board;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }
}
