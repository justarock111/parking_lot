import java.util.*;

public class Trello {
    private LinkedHashMap<String, Board> boards;
    private Map<String, Board> trelloListMap;
    private Map<String, Board> cardMap;
    private LinkedHashMap<String, User> members;

    public Trello(){
        boards = new LinkedHashMap<>();
        members = new LinkedHashMap<>();

        trelloListMap = new HashMap<>();
        cardMap = new HashMap<>();
    }

    public void addMember(String userId, String name, String email){
        User user = new User(userId, name, email);
        members.put(userId, user);
    }

    public void createBoard(String name){
        Board board = new Board(name);
        boards.put(board.getId(), board);

        System.out.println("Created board: " + board.getId());
    }

    public void deleteBoard(String id){
        Board board = boards.get(id);
        Collection<TrelloList> trelloLists = board.getTrelloLists();

        for(TrelloList trelloList: trelloLists){
            trelloListMap.remove(trelloList.getId());
        }
        boards.remove(id);
    }

    public void showBoard(String id){
        Board board = boards.get(id);
        if(board == null){
            System.out.println("Board " + id + " does not exist");
            return;
        }
        System.out.println(board.toString());
    }

    public void showBoards(){
        if(boards.isEmpty()){
            System.out.println("No boards");
            return;
        }

        List<String> result = new ArrayList<>();
        for(Board board: boards.values()){
            result.add(board.toString());
        }

        System.out.println(result);
    }

    public void modifyBoard(String id, String thirdWord, String fourthWord){
        Board board = boards.get(id);

        switch(thirdWord){
            case "ADD_MEMBER":
                String memberId = fourthWord;
                User member = members.get(memberId);
                board.addMember(member);
                break;
            case "REMOVE_MEMBER":
                board.removeMember(fourthWord);
                break;
            default:
                board.modify(thirdWord, fourthWord);
        }
    }

    public void createList(String boardId, String name){
        Board board = boards.get(boardId);
        String trelloListId = board.createList(name);

        trelloListMap.put(trelloListId, board);
        System.out.println("Created List: " + trelloListId);
    }

    public void deleteList(String listId){
        Board board = trelloListMap.get(listId);

        board.deleteList(listId);
        trelloListMap.remove(listId);
    }

    public void modifyList(String listId, String attrKey, String attrValue){
        Board board = trelloListMap.get(listId);
        board.modifyList(listId, attrKey, attrValue);
    }

    public void showList(String listId){
        Board board = trelloListMap.get(listId);
        board.showList(listId);
    }

    public void createCard(String listId, String name){
        Board board = trelloListMap.get(listId);
        String cardId = board.createCard(listId, name);

        cardMap.put(cardId, board);
        System.out.println("Created Card: " + cardId);
    }

    public void moveCard(String cardId, String listId){
        Board oldBoard = cardMap.get(cardId);
        Card card = oldBoard.getCard(cardId);
        oldBoard.deleteCard(cardId);

        Board newBoard = trelloListMap.get(listId);
        newBoard.addCard(listId, card);

        cardMap.put(card.getId(), newBoard);
    }

    public void deleteCard(String cardId){
        Board board = cardMap.get(cardId);

        board.deleteCard(cardId);
        cardMap.remove(cardId);
    }

    public void modifyCard(String[] lineArgs){
        String cardId = lineArgs[1], thirdWord = lineArgs[2];
        Board board = cardMap.get(cardId);
        switch(thirdWord){
            case "ASSIGN":
                String userId = lineArgs[3];
                board.assignCard(cardId, userId);
                break;
            case "UNASSIGN":
                board.unassignCard(cardId);
                break;
            default:
                String attrKey = lineArgs[2], attrValue = lineArgs[3];
                board.modifyCard(cardId, attrKey, attrValue);
        }
    }

    public void showCard(String cardId){
        Board board = cardMap.get(cardId);
        board.showCard(cardId);
    }

}