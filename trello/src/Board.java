import lombok.Getter;

import java.io.Serializable;
import java.util.*;

public class Board{
    @Getter
    private String id;
    private String name;
    private Access privacy;
    private String url;
    private Map<String, User> members;
    @Getter
    private Map<String, TrelloList> trelloLists;
    private Map<String, TrelloList> cardMap;

    public Board(String name){
        id = UUID.randomUUID().toString();
        this.name = name;
        this.privacy = Access.PUBLIC;
        this.url = "https:/www.trello.com/" + id;

        members = new HashMap<>();
        trelloLists = new HashMap<>();
        cardMap = new HashMap<>();
    }

    public Collection<TrelloList> getTrelloLists(){
        return trelloLists.values();
    }

   public void modify(String attrKey, String attrValue){
        switch(attrKey){
            case "name":
                this.name = attrValue;
                break;
            case "privacy":
                this.privacy = Access.valueOf(attrValue);
        }
   }

   public String createList(String name){
        TrelloList trelloList = new TrelloList(name);
        trelloLists.put(trelloList.getId(), trelloList);

        return trelloList.getId();
   }

   public void deleteList(String listId){
        TrelloList trelloList = trelloLists.get(listId);
        for(Card card: trelloList.getCards()){
            cardMap.remove(card.getId());
        }
        trelloLists.remove(listId);
   }

   public void modifyList(String listId, String attrKey, String attrValue){
        TrelloList trelloList = trelloLists.get(listId);
        trelloList.modify(attrKey, attrValue);
   }

   public void showList(String listId){
       TrelloList trelloList = trelloLists.get(listId);
       String output = trelloList.toString();
       System.out.println(output);
   }

   public Card getCard(String cardId){
        TrelloList trelloList = cardMap.get(cardId);
        return trelloList.getCard(cardId);
   }

   public String createCard(String listId, String name){
       TrelloList trelloList = trelloLists.get(listId);
       String cardId = trelloList.createCard(name);

       cardMap.put(cardId, trelloList);
       return cardId;
   }

   public void addCard(String listId, Card card){
       TrelloList trelloList = trelloLists.get(listId);
       trelloList.addCard(card);

       cardMap.put(card.getId(), trelloList);
   }

    public void deleteCard(String cardId){
        TrelloList trelloList = cardMap.get(cardId);
        trelloList.deleteCard(cardId);

        cardMap.remove(cardId);
    }

    public void assignCard(String cardId, String userId){
        TrelloList trelloList = cardMap.get(cardId);
        User user = members.get(userId);
        trelloList.assignCard(cardId, user);
    }

    public void unassignCard(String cardId){
        TrelloList trelloList = cardMap.get(cardId);
        trelloList.unassignCard(cardId);
    }

    public void modifyCard(String cardId, String attrKey, String attrValue){
        TrelloList trelloList = cardMap.get(cardId);
        trelloList.modifyCard(cardId, attrKey, attrValue);
    }

    public void showCard(String cardId){
        TrelloList trelloList = cardMap.get(cardId);
        trelloList.showCard(cardId);
    }

    public void addMember(User member){
        members.put(member.getId(), member);
    }

    public void removeMember(String memberId){
        members.remove(memberId);
    }

    @Override
    public String toString(){
        List<String> pairList = new ArrayList<>();

        String idString = String.format("\"id\": \'%s\"", id);
        pairList.add(idString);
        String nameString = String.format("\"name\": \'%s\"", name);
        pairList.add(nameString);
        String privacyString = String.format("\"privacy\": \"%s\"", privacy);
        pairList.add(privacyString);

        if(!members.isEmpty()){
            pairList.add(buildMembersString());
        }

        if(!trelloLists.isEmpty()){
            pairList.add(buildListsString());
        }

        return "{" + String.join(", ", pairList) + "}";
    }

    private String buildMembersString(){
        List<String> membersList = new ArrayList<>();
        for(User user: members.values()){
            membersList.add(user.toString());
        }

        return "\"members\": " + membersList;
    }

    private String buildListsString(){
        List<String> trelloListStrings = new ArrayList<>();
        for(TrelloList trelloList: trelloLists.values()){
            trelloListStrings.add(trelloList.toString());
        }

        return "\"lists\": " + trelloListStrings;
    }
}
