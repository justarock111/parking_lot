import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Card {
    @Getter
    private String id;
    private String name;
    private String description;
    private User assignedTo;

    public Card(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;;
    }

   public void modify(String attrKey, String attrValue){
       switch(attrKey){
           case "name":
               this.name = attrValue;
               break;
           case "description":
               this.description = attrValue;
       }
   }

   public void assign(User user){
        this.assignedTo = user;
   }

    public void unassign(){
        this.assignedTo = null;
    }

    public String toString(){
        List<String> pairList = new ArrayList<>();

        String idString = String.format("\"id\": \'%s\"", id);
        pairList.add(idString);
        String nameString = String.format("\"name\": \"%s\"", name);
        pairList.add(nameString);

        if(assignedTo != null){
            String assignedToString = String.format("\"assignedTo\": \"%s\"", assignedTo.getId());
            pairList.add(assignedToString);
        }

        if(description != null){
            String descriptionString = String.format("\"description\": \"%s\"", description);
            pairList.add(descriptionString);
        }

        return "{" + String.join(", ", pairList) + "}";
    }

}
