import java.util.LinkedList;

public class Game {
    private State state;
    private Board board;
    private LinkedList<Player> players;

    public Game(){
        this.state = State.PLAYING;
        this.board = new Board();
        this.players = new LinkedList<>();
    }

    public void addPlayer(char pieceTypeChar, String name){
        PieceType pieceType = PieceType.getPieceType(pieceTypeChar);
        Player player = new Player(pieceType, name);

        if(pieceType == PieceType.CROSS){
            players.addFirst(player);
        } else {
            players.addLast(player);
        }
    }
    
    public String addPieceType(int row, int col){
        Player currentPlayer = players.getFirst();
        char piece = board.getPiece(row, col);

        if(piece != '-') {
            return "Invalid Move";
        }

        state = board.addPiece(currentPlayer.getPieceType(), row, col);
        String output = board.toString();
        if(state == State.WON){
            String winString = String.format("\n %s won the game", currentPlayer.getName());
            output += winString;
        } else if(state == State.GAME_OVER){
            String overString = "\nGame Over";
            output += overString;
        }

        players.pollFirst();
        players.addLast(currentPlayer);
        return output;

    }

    public String getBoardString(){
        return board.toString();
    }
}
