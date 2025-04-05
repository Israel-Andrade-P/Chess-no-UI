package chess;

import boardgame.Position;

public class ChessPosition {
    private char column;
    private int row;
    
    
    public ChessPosition(char column, int row) {
        isPositionValid(column, row);
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    protected Position toPosition(){
        return new Position(8 - row, column - 'a');
    }

    protected ChessPosition fromPosition(Position position){
        return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());
    }

    private void isPositionValid(char column, int row){
        if (row < 0 || row > 8 || column < 'a' || column > 'h') {
            throw new ChessException("That is not a valid position.");
        }
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
