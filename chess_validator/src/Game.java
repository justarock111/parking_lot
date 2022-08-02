import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private Queue<Player> players;
    private Board board;

    public Game(){
        players = new LinkedList<>();
        addPlayers();
        board = new Board();
    }

    private void addPlayers(){
        Player p1 = new Player("Player1", Side.WHITE);
        Player p2 = new Player("Player2", Side.BLACK);
        players.add(p1);
        players.add(p2);
    }

    public String move(String[] posArray){
        String start = posArray[0], end = posArray[1];
        Player currentPlayer = players.peek();

        boolean canMove = move(currentPlayer, start, end);
        if(canMove){
            players.poll();
            players.add(currentPlayer);
            return displayBoard();
        } else {
            return "Invalid Move";
        }
    }

    private boolean move(Player currentPlayer, String start, String end){
        int[] startPos = getPosition(start), endPos = getPosition(end);
        Piece startPiece = board.getPiece(startPos);
        if(startPiece == null || startPiece.getSide() != currentPlayer.getSide()){
            return false;
        }

        Piece endPiece = board.getPiece(endPos);
        boolean isCapture = endPiece != null;
        if(isCapture && endPiece.getSide() == currentPlayer.getSide()){
            return false;
        } else {
            return board.move(startPiece, endPos, isCapture);
        }
    }

    private int[] getPosition(String string){
        int[] pos = new int[2];
        pos[1] = string.charAt(0) - 'a';
        pos[0] = (string.charAt(1) - '0') * -1 + board.BOARD_SIZE;

        return pos;
    }

    public String displayBoard(){
        return board.display();
    }
}
