package boardgame;

public class Board {
    private final int rows;
    private final int columns;
    private Piece pieces[][];

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board. There must be at least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece getPiece(int row, int column){
        if (!positionExists(row, column)){
            throw new BoardException("Position is not on the board.");
        }
        return pieces[row][column];
    }

    public Piece getPiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position is not on the board.");
        }
        return getPiece(position.getRow(),position.getColumn());
    }
    
    public void placePiece(Piece piece, Position position) {
        if (isTherePiece(position)) {
            throw new BoardException("There is already a piece on position: " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.setPosition(position);
    }

    public Piece removePiece(Position position){
        if (!positionExists(position)) {
            throw new BoardException("Position is not on the board.");
        }
        if (!isTherePiece(position)){
            return null;
        }
        Piece aux = getPiece(position);
        aux.setPosition(null);
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }
    
    public boolean isTherePiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position is not on the board.");
        }
        return getPiece(position) != null;
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
