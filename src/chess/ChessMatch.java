package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private List<Piece> piecesOnTheBoard;
    private List<Piece> capturedPieces;

    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        piecesOnTheBoard = new ArrayList<>();
        capturedPieces = new ArrayList<>();
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getColumns(); j++){
                pieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return pieces;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePos){
        Position position = sourcePos.toPosition();
        validateSourcePosition(position);
        return board.getPiece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos){
        Position source = sourcePos.toPosition();
        Position target = targetPos.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Your King will be in check. Move not allowed.");
        }

        check = testCheck(opponent(currentPlayer)) ? true : false;

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        }
        else {
            nextTurn();
        }
        
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);
        
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void validateSourcePosition(Position source) {
        if (!board.isTherePiece(source)) {
            throw new ChessException("There is no piece on source position.");
        }

        if (currentPlayer != ((ChessPiece) board.getPiece(source)).getColor()){
            throw new ChessException("The chosen piece is not yours.");
        }

        if (!board.getPiece(source).isThereAnyPossibleMove()) {
            throw new ChessException("There are no possible moves for the chosen piece.");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
       if (!board.getPiece(source).isMovePossible(target)) {
            throw new ChessException("That is not a valid move for the chosen piece.");
       }
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king (Color color){
        return (ChessPiece) piecesOnTheBoard.stream()
        .filter(piece -> piece instanceof King && ((ChessPiece) piece).getColor() == color)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("There is no " + color + "king on the board."));
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPices = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor() == opponent(color)).toList();
        for (Piece p : opponentPices){
            boolean[][] moves = p.possibleMoves();
            if (moves[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if (!testCheck(color)) {
            return false;
        }

        List<Piece> pieces = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor() == color).toList();
        for (Piece p : pieces){
            boolean[][] moves = p.possibleMoves();
            for (int i = 0; i < moves.length; i++){
                for (int j = 0; j < moves.length; j++){
                    if (moves[i][j]) {
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean check = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!check) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
		
	}

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }
}
