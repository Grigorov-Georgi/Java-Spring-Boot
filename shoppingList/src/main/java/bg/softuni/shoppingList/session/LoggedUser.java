package bg.softuni.shoppingList.session;

import bg.softuni.shoppingList.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private long id;

    public void login(User user){
        this.id = user.getId();
    }

    public void logout(){
        this.id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
