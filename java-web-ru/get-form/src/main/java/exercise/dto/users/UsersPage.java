package exercise.dto.users;

import exercise.model.User;
import java.util.List;

public class UsersPage {

    private String term;
    private List<User> users;

    public UsersPage(String term, List<User> users) {
        this.term = term;
        this.users = users;
    }

    public String getTerm() {
        return term;
    }

    public List<User> getUsers() {
        return users;
    }
}
// END
