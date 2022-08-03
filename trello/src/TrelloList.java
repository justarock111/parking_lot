import lombok.Getter;

import java.util.*;

public class TrelloList {
    @Getter
    private String id;
    private String name;
    private Access privacy;
    private Map<String, Card> cards;

    public TrelloList(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        cards = new HashMap<>();
    }

    public Collection<Card> getCards(){
        return cards.values();
    }

    public void modify(String attrKey, String attrValue) {
        switch (attrKey) {
            case "name":
                this.name = attrValue;
                break;
            case "privacy":
                this.privacy = Access.valueOf(attrValue);
        }
    }

    public void show() {

    }

    public Card getCard(String cardId){
        return cards.get(cardId);
    }

    public String createCard(String name) {
        Card card = new Card(name);
        return addCard(card);
    }

    public String addCard(Card card){
        cards.put(card.getId(), card);
        return card.getId();
    }

    public void deleteCard(String cardId) {
        cards.remove(cardId);
    }

    public void assignCard(String cardId, User user){
        Card card = cards.get(cardId);
        card.assign(user);
    }

    public void unassignCard(String cardId){
        Card card = cards.get(cardId);
        card.unassign();
    }

    public void modifyCard(String cardId, String attrKey, String attrValue) {
        Card card = cards.get(cardId);
        card.modify(attrKey, attrValue);
    }

    public void showCard(String cardId) {
        Card card = cards.get(cardId);
        if(card == null){
            System.out.println("Card " + cardId + " does not exist");
            return;
        }

        String output = card.toString();
        System.out.println(output);
    }


    public String toString(){
        List<String> pairList = new ArrayList<>();

        String idString = String.format("\"id\": \'%s\"", id);
        pairList.add(idString);
        String nameString = String.format("\"name\": \"%s\"", name);
        pairList.add(nameString);

        if(!cards.isEmpty()){
            pairList.add(buildCardsString());
        }

        return "{" + String.join(", ", pairList) + "}";
    }

    private String buildCardsString(){
        List<String> cardStrings = new ArrayList<>();
        for(Card card: cards.values()){
            cardStrings.add(card.toString());
        }

        return "\"cards\": " + cardStrings;
    }
}
