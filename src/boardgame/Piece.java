package boardgame;

public abstract class Piece {
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

    public abstract boolean[][] possibleMoves();

    public boolean isMovePossible(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] moves = possibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves.length; j++){
                if (moves[i][j]) return true;
            }
        }
        return false;
    }
}
