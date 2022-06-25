package bg.softuni.shoppingList.session;

import bg.softuni.shoppingList.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;

@Component
@SessionScope
public class LoggedUser {
    private long id;
    private BigDecimal total;

    public void login(User user){
        this.id = user.getId();
        this.total = BigDecimal.valueOf(0);
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
