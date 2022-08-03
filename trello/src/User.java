import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Getter
    private String id, name, email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String toString() {
        List<String> pairList = new ArrayList<>();

        String idString = String.format("\"id\": \'%s\"", id);
        pairList.add(idString);
        String nameString = String.format("\"name\": \'%s\"", name);
        pairList.add(nameString);
        String emailString = String.format("\"email\": \"%s\"", email);
        pairList.add(emailString);

        return "{" + String.join(", ", pairList) + "}";
    }
}
